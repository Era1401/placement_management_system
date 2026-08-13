package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.placement.PlacementRequest;
import com.anudip.placement_management_system.dto.placement.PlacementResponse;
import com.anudip.placement_management_system.entity.Placement;
import org.springframework.stereotype.Component;

@Component
public class PlacementMapper {

    public Placement toEntity(
            PlacementRequest request) {

        Placement placement = new Placement();

        placement.setPackageAmount(
                request.getPackageAmount()
        );

        placement.setPlacementDate(
                request.getPlacementDate()
        );

        placement.setStatus(
                request.getStatus()
        );

        return placement;
    }

    public PlacementResponse toResponse(
            Placement placement) {

        PlacementResponse response =
                new PlacementResponse();

        response.setId(placement.getId());

        response.setPackageAmount(
                placement.getPackageAmount()
        );

        response.setPlacementDate(
                placement.getPlacementDate()
        );

        response.setStatus(
                placement.getStatus()
        );

        if (placement.getStudent() != null) {

            response.setStudentId(
                    placement.getStudent().getId()
            );

            response.setStudentName(
                    placement.getStudent().getName()
            );
        }

        if (placement.getCompany() != null) {

            response.setCompanyId(
                    placement.getCompany().getId()
            );

            response.setCompanyName(
                    placement.getCompany().getName()
            );
        }

        if (placement.getJob() != null) {

            response.setJobId(
                    placement.getJob().getId()
            );

            response.setJobTitle(
                    placement.getJob().getTitle()
            );
        }

        return response;
    }

    public void updateEntity(
            Placement placement,
            PlacementRequest request) {

        placement.setPackageAmount(
                request.getPackageAmount()
        );

        placement.setPlacementDate(
                request.getPlacementDate()
        );

        if (request.getStatus() != null) {

            placement.setStatus(
                    request.getStatus()
            );
        }
    }
}