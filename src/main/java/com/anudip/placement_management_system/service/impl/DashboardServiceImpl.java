package com.anudip.placement_management_system.service.impl;


import com.anudip.placement_management_system.dto.dashboard.DashboardResponse;
import com.anudip.placement_management_system.repository.*;
import com.anudip.placement_management_system.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final InterviewRepository interviewRepository;
    private final PlacementRepository placementRepository;

    public DashboardServiceImpl(
            StudentRepository studentRepository,
            CompanyRepository companyRepository,
            JobRepository jobRepository,
            ApplicationRepository applicationRepository,
            InterviewRepository interviewRepository,
            PlacementRepository placementRepository) {

        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.interviewRepository = interviewRepository;
        this.placementRepository = placementRepository;
    }

    @Override
    public DashboardResponse getDashboardStatistics() {

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalStudents(
                studentRepository.count());

        response.setTotalCompanies(
                companyRepository.count());

        response.setTotalJobs(
                jobRepository.count());

        response.setTotalApplications(
                applicationRepository.count());

        response.setTotalInterviews(
                interviewRepository.count());

        response.setTotalPlacements(
                placementRepository.count());

        return response;
    }
}