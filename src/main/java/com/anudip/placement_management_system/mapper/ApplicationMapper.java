package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.application.ApplicationRequest;
import com.anudip.placement_management_system.dto.application.ApplicationResponse;
import com.anudip.placement_management_system.entity.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public Application toEntity(
            ApplicationRequest request) {

        Application application = new Application();

        application.setStatus(request.getStatus());

        return application;
    }

    public ApplicationResponse toResponse(
            Application application) {

        ApplicationResponse response =
                new ApplicationResponse();

        response.setId(application.getId());
        response.setStatus(application.getStatus());

        if (application.getStudent() != null) {

            response.setStudentId(
                    application.getStudent().getId()
            );

            response.setStudentName(
                    application.getStudent().getName()
            );
        }

        if (application.getJob() != null) {

            response.setJobId(
                    application.getJob().getId()
            );

            response.setJobTitle(
                    application.getJob().getTitle()
            );
        }

        return response;
    }

    public void updateEntity(
            Application application,
            ApplicationRequest request) {

        if (request.getStatus() != null) {
            application.setStatus(
                    request.getStatus()
            );
        }
    }
}