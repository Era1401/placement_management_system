package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.placement.PlacementRequest;
import com.anudip.placement_management_system.dto.placement.PlacementResponse;
import com.anudip.placement_management_system.enums.PlacementStatus;

import java.util.List;

public interface PlacementService {
    PlacementResponse createPlacement(PlacementRequest request);

    PlacementResponse getPlacementById(Long id);

    List<PlacementResponse> getAllPlacements();

    PlacementResponse getPlacementByStudent(Long studentId);

    List<PlacementResponse> getPlacementsByCompany(Long companyId);

    PlacementResponse updatePlacement(
            Long id,
            PlacementRequest request
    );

}
