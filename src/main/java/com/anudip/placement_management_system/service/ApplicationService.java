package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.application.ApplicationRequest;
import com.anudip.placement_management_system.dto.application.ApplicationResponse;
import com.anudip.placement_management_system.enums.ApplicationStatus;

import java.util.List;

public interface ApplicationService {

    ApplicationResponse createApplication(ApplicationRequest request);

    ApplicationResponse getApplicationById(Long id);

    List<ApplicationResponse> getAllApplications();

    ApplicationResponse updateApplication(
            Long id,
            ApplicationRequest request);

    void rejectApplication(Long id);
}