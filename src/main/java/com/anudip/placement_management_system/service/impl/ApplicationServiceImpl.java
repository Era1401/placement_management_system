package com.anudip.placement_management_system.service.impl;

import com.anudip.placement_management_system.dto.application.ApplicationRequest;
import com.anudip.placement_management_system.dto.application.ApplicationResponse;
import com.anudip.placement_management_system.entity.Application;
import com.anudip.placement_management_system.entity.Job;
import com.anudip.placement_management_system.entity.Student;
import com.anudip.placement_management_system.enums.ApplicationStatus;
import com.anudip.placement_management_system.repository.ApplicationRepository;
import com.anudip.placement_management_system.repository.JobRepository;
import com.anudip.placement_management_system.repository.StudentRepository;
import com.anudip.placement_management_system.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final JobRepository jobRepository;

    public ApplicationServiceImpl(
            ApplicationRepository applicationRepository,
            StudentRepository studentRepository,
            JobRepository jobRepository) {

        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public ApplicationResponse createApplication(ApplicationRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = new Application();

        application.setStudent(student);
        application.setJob(job);
        application.setStatus(ApplicationStatus.APPLIED);

        Application savedApplication =
                applicationRepository.save(application);

        return mapToResponse(savedApplication);
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        return mapToResponse(application);
    }

    @Override
    public List<ApplicationResponse> getAllApplications() {

        return applicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ApplicationResponse updateApplication(
            Long id,
            ApplicationRequest request) {

        Application application =
                applicationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setStatus(request.getStatus());

        Application updatedApplication =
                applicationRepository.save(application);

        return mapToResponse(updatedApplication);
    }

    @Override
    public void rejectApplication(Long id) {

        Application application =
                applicationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setStatus(ApplicationStatus.REJECTED);

        applicationRepository.save(application);
    }

    private ApplicationResponse mapToResponse(
            Application application) {

        ApplicationResponse response = new ApplicationResponse();

        response.setId(application.getId());
        response.setStatus(application.getStatus());

        if (application.getStudent() != null) {
            response.setStudentId(
                    application.getStudent().getId());

            response.setStudentName(
                    application.getStudent().getName());
        }

        if (application.getJob() != null) {
            response.setJobId(
                    application.getJob().getId());

            response.setJobTitle(
                    application.getJob().getTitle());
        }

        return response;
    }
}