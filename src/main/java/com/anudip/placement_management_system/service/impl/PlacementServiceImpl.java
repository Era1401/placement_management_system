package com.anudip.placement_management_system.service.impl;


import com.anudip.placement_management_system.dto.placement.PlacementRequest;
import com.anudip.placement_management_system.dto.placement.PlacementResponse;
import com.anudip.placement_management_system.entity.Company;
import com.anudip.placement_management_system.entity.Job;
import com.anudip.placement_management_system.entity.Placement;
import com.anudip.placement_management_system.entity.Student;
import com.anudip.placement_management_system.enums.PlacementStatus;
import com.anudip.placement_management_system.repository.CompanyRepository;
import com.anudip.placement_management_system.repository.JobRepository;
import com.anudip.placement_management_system.repository.PlacementRepository;
import com.anudip.placement_management_system.repository.StudentRepository;
import com.anudip.placement_management_system.service.PlacementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacementServiceImpl implements PlacementService {

    private final PlacementRepository placementRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;

    public PlacementServiceImpl(
            PlacementRepository placementRepository,
            StudentRepository studentRepository,
            CompanyRepository companyRepository,
            JobRepository jobRepository) {

        this.placementRepository = placementRepository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public PlacementResponse createPlacement(
            PlacementRequest request) {

        Student student =
                studentRepository.findById(
                                request.getStudentId())
                        .orElseThrow(() ->
                                new RuntimeException("Student not found"));

        Company company =
                companyRepository.findById(
                                request.getCompanyId())
                        .orElseThrow(() ->
                                new RuntimeException("Company not found"));

        Job job =
                jobRepository.findById(
                                request.getJobId())
                        .orElseThrow(() ->
                                new RuntimeException("Job not found"));

        Placement placement = new Placement();

        placement.setStudent(student);
        placement.setCompany(company);
        placement.setJob(job);
        placement.setPackageAmount(
                request.getPackageAmount());
        placement.setPlacementDate(
                request.getPlacementDate());
        placement.setStatus(PlacementStatus.PLACED);

        Placement savedPlacement =
                placementRepository.save(placement);

        return mapToResponse(savedPlacement);
    }

    @Override
    public PlacementResponse getPlacementById(Long id) {

        Placement placement =
                placementRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Placement not found"));

        return mapToResponse(placement);
    }

    @Override
    public List<PlacementResponse> getAllPlacements() {

        return placementRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PlacementResponse getPlacementByStudent(
            Long studentId) {

        Placement placement =
                placementRepository.findByStudentId(studentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Placement not found for student"));

        return mapToResponse(placement);
    }

    @Override
    public List<PlacementResponse> getPlacementsByCompany(
            Long companyId) {

        return placementRepository
                .findByCompanyId(companyId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PlacementResponse updatePlacement(
            Long id,
            PlacementRequest request) {

        Placement placement =
                placementRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Placement not found"));

        placement.setPackageAmount(
                request.getPackageAmount());

        placement.setPlacementDate(
                request.getPlacementDate());

        if (request.getStatus() != null) {
            placement.setStatus(request.getStatus());
        }

        Placement updatedPlacement =
                placementRepository.save(placement);

        return mapToResponse(updatedPlacement);
    }

    private PlacementResponse mapToResponse(
            Placement placement) {

        PlacementResponse response =
                new PlacementResponse();

        response.setId(placement.getId());
        response.setPackageAmount(
                placement.getPackageAmount());
        response.setPlacementDate(
                placement.getPlacementDate());
        response.setStatus(
                placement.getStatus());

        if (placement.getStudent() != null) {
            response.setStudentId(
                    placement.getStudent().getId());

            response.setStudentName(
                    placement.getStudent().getName());
        }

        if (placement.getCompany() != null) {
            response.setCompanyId(
                    placement.getCompany().getId());

            response.setCompanyName(
                    placement.getCompany().getName());
        }

        if (placement.getJob() != null) {
            response.setJobId(
                    placement.getJob().getId());

            response.setJobTitle(
                    placement.getJob().getTitle());
        }

        return response;
    }
}