const API = '/api';
const numberFormat = new Intl.NumberFormat();
const $ = (selector) => document.querySelector(selector);
let toastTimer;

const safe = (value, fallback = '—') => value === null || value === undefined || value === '' ? fallback : String(value);
const escapeHtml = (value) => String(value ?? '').replace(/[&<>"']/g, (char) => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;' })[char]);
const initials = (name) => safe(name, '?').split(/\s+/).filter(Boolean).slice(0, 2).map((part) => part[0]).join('').toUpperCase();

async function getJson(path) {
  const response = await fetch(`${API}${path}`, { headers: { Accept: 'application/json' } });
  if (!response.ok) throw new Error(`${path}: ${response.status} ${response.statusText}`);
  return response.json();
}

function setConnection(state, message) {
  const el = $('#connection-status');
  el.className = `live-status ${state}`;
  el.lastChild.textContent = ` ${message}`;
}

function renderStats(stats, jobs) {
  const values = {
    students: stats.totalStudents,
    companies: stats.totalCompanies,
    jobs: jobs.filter((job) => String(job.status).toUpperCase() === 'OPEN').length,
    placements: stats.totalPlacements
  };
  Object.entries(values).forEach(([key, value]) => {
    const node = document.querySelector(`[data-stat="${key}"]`);
    if (node) node.textContent = numberFormat.format(Number(value ?? 0));
  });
}

function renderPipeline(applications) {
  const counts = new Map();
  applications.forEach((application) => {
    const status = String(application.status || 'UNKNOWN').toUpperCase();
    counts.set(status, (counts.get(status) || 0) + 1);
  });
  const stages = [
    { label: 'Applied', keys: ['APPLIED'] },
    { label: 'Shortlisted', keys: ['SHORTLISTED'] },
    { label: 'Selected', keys: ['SELECTED'] },
    { label: 'Rejected', keys: ['REJECTED'] }
  ].map((stage) => ({ ...stage, count: stage.keys.reduce((sum, key) => sum + (counts.get(key) || 0), 0) }));
  const max = Math.max(1, ...stages.map((stage) => stage.count));
  $('#pipeline-list').innerHTML = stages.map((stage) => `
    <div class="pipeline-row">
      <span class="pipeline-label">${stage.label}</span>
      <div class="pipeline-track" role="progressbar" aria-label="${stage.label}" aria-valuemin="0" aria-valuemax="${max}" aria-valuenow="${stage.count}"><div class="pipeline-fill" style="width:${Math.max(stage.count ? 4 : 0, stage.count / max * 100)}%"></div></div>
      <span class="pipeline-count">${numberFormat.format(stage.count)}</span>
    </div>`).join('');
  $('#application-total').textContent = `${numberFormat.format(applications.length)} applications`;
}

function renderChart(placements) {
  const now = new Date();
  const months = Array.from({ length: 6 }, (_, index) => new Date(now.getFullYear(), now.getMonth() - 5 + index, 1));
  const buckets = months.map((month) => ({ month, count: 0 }));
  placements.forEach((placement) => {
    if (!placement.placementDate) return;
    const date = new Date(`${placement.placementDate}T00:00:00`);
    const bucket = buckets.find(({ month }) => month.getFullYear() === date.getFullYear() && month.getMonth() === date.getMonth());
    if (bucket) bucket.count += 1;
  });
  const max = Math.max(1, ...buckets.map((bucket) => bucket.count));
  const axis = `<div class="chart-axis" aria-hidden="true"><span>${max}</span><span>${Math.round(max / 2)}</span><span>0</span></div>`;
  const columns = buckets.map(({ month, count }) => `
    <div class="chart-column" title="${month.toLocaleDateString(undefined, { month: 'long', year: 'numeric' })}: ${count} placement${count === 1 ? '' : 's'}">
      <div class="chart-bar" style="height:${count ? Math.max(5, count / max * 88) : 2}%"></div>
      <span class="chart-month">${month.toLocaleDateString(undefined, { month: 'short' })}</span>
    </div>`).join('');
  $('#placement-chart').innerHTML = axis + columns;
}

function renderInterviews(interviews, applications) {
  const applicationById = new Map(applications.map((application) => [Number(application.id), application]));
  const today = new Date(); today.setHours(0, 0, 0, 0);
  const upcoming = interviews.filter((interview) => interview.interviewDate && new Date(`${interview.interviewDate}T00:00:00`) >= today)
    .sort((a, b) => `${a.interviewDate}T${a.interviewTime || '00:00'}`.localeCompare(`${b.interviewDate}T${b.interviewTime || '00:00'}`)).slice(0, 3);
  if (!upcoming.length) {
    $('#interview-list').innerHTML = '<div class="empty-state">No upcoming interviews are scheduled.</div>';
    return;
  }
  $('#interview-list').innerHTML = upcoming.map((interview, index) => {
    const application = applicationById.get(Number(interview.applicationId));
    const name = application?.studentName || `Application #${safe(interview.applicationId)}`;
    const job = application?.jobTitle || 'Interview';
    const date = new Date(`${interview.interviewDate}T${interview.interviewTime || '00:00'}`);
    const when = `${date.toLocaleDateString(undefined, { month: 'short', day: 'numeric' })} · ${date.toLocaleTimeString(undefined, { hour: 'numeric', minute: '2-digit' })}`;
    return `<div class="list-row"><span class="person-initials" style="background:${index % 2 ? 'var(--tea-green)' : 'var(--beige)'}">${escapeHtml(initials(name))}</span><span class="row-copy"><strong>${escapeHtml(name)}</strong><span>${escapeHtml(job)} · ${escapeHtml(interview.mode || 'Interview')}</span></span><span class="row-meta">${escapeHtml(when)}</span></div>`;
  }).join('');
}

function renderPlacements(placements) {
  const recent = [...placements].sort((a, b) => String(b.placementDate || '').localeCompare(String(a.placementDate || ''))).slice(0, 3);
  $('#placement-total').textContent = `${numberFormat.format(placements.length)} total`;
  if (!recent.length) {
    $('#placement-list').innerHTML = '<div class="empty-state">No placement records yet.</div>';
    $('#placement-note').textContent = 'New placement records will appear here.';
    return;
  }
  $('#placement-list').innerHTML = recent.map((placement, index) => {
    const name = placement.studentName || `Student #${safe(placement.studentId)}`;
    const role = placement.jobTitle || 'Placement';
    const company = placement.companyName || 'Company not specified';
    return `<div class="list-row"><span class="person-initials" style="background:${['var(--beige)', 'var(--tea-green)', 'var(--papaya-whip)'][index]}">${escapeHtml(initials(name))}</span><span class="row-copy"><strong>${escapeHtml(name)}</strong><span>${escapeHtml(role)} · ${escapeHtml(company)}</span></span><span class="status-pill">${escapeHtml(String(placement.status || 'Recorded').replaceAll('_', ' ').toLowerCase())}</span></div>`;
  }).join('');
  $('#placement-note').textContent = `${numberFormat.format(placements.length)} placement record${placements.length === 1 ? '' : 's'} in the system.`;
}

function showToast(message) {
  const toast = $('#toast');
  toast.textContent = message;
  toast.classList.add('visible');
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => toast.classList.remove('visible'), 3200);
}

async function loadDashboard() {
  setConnection('', 'Refreshing…');
  try {
    const [stats, jobs, applications, interviews, placements] = await Promise.all([
      getJson('/dashboard'), getJson('/jobs'), getJson('/applications'), getJson('/interviews'), getJson('/placements')
    ]);
    renderStats(stats, jobs);
    renderPipeline(applications);
    renderChart(placements);
    renderInterviews(interviews, applications);
    renderPlacements(placements);
    setConnection('connected', 'Connected to backend');
  } catch (error) {
    console.error('Unable to load placement dashboard:', error);
    setConnection('error', 'Backend unavailable');
    $('#placement-chart').innerHTML = '<div class="chart-loading error-note">Could not load placement history. Check the backend connection and refresh.</div>';
    $('#interview-list').innerHTML = '<div class="empty-state error-note">Interview data is unavailable.</div>';
    $('#pipeline-list').innerHTML = '<div class="empty-state error-note">Application data is unavailable.</div>';
    $('#placement-list').innerHTML = '<div class="empty-state error-note">Placement data is unavailable.</div>';
    showToast('Could not load dashboard data. Check that the backend is running.');
  }
}

$('#today-label').textContent = new Date().toLocaleDateString(undefined, { weekday: 'long', day: 'numeric', month: 'long', year: 'numeric' });
$('#refresh').addEventListener('click', loadDashboard);
$('#search').addEventListener('input', (event) => {
  const query = event.target.value.trim().toLocaleLowerCase();
  document.querySelectorAll('.list-row, .pipeline-row').forEach((row) => {
    row.hidden = Boolean(query) && !row.textContent.toLocaleLowerCase().includes(query);
  });
});
document.querySelectorAll('.nav-link').forEach((link) => link.addEventListener('click', () => {
  document.querySelectorAll('.nav-link').forEach((item) => item.classList.toggle('active', item === link));
}));
loadDashboard();
