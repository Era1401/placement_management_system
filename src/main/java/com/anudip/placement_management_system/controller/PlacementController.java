package com.anudip.placement_management_system.controller;

import com.anudip.placement_management_system.dto.placement.PlacementRequest;
import com.anudip.placement_management_system.dto.placement.PlacementResponse;
import com.anudip.placement_management_system.service.PlacementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/placements")
public class PlacementController {

    private final PlacementService placementService;

    public PlacementController(
            PlacementService placementService) {

        this.placementService = placementService;
    }

    @PostMapping
    public ResponseEntity<PlacementResponse> createPlacement(
            @RequestBody PlacementRequest request) {

        PlacementResponse response =
                placementService.createPlacement(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlacementResponse> getPlacementById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                placementService.getPlacementById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<PlacementResponse>>
    getAllPlacements() {

        return ResponseEntity.ok(
                placementService.getAllPlacements()
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<PlacementResponse>
    getPlacementByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                placementService.getPlacementByStudent(studentId)
        );
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<PlacementResponse>>
    getPlacementsByCompany(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                placementService.getPlacementsByCompany(companyId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlacementResponse> updatePlacement(
            @PathVariable Long id,
            @RequestBody PlacementRequest request) {

        return ResponseEntity.ok(
                placementService.updatePlacement(id, request)
        );
    }
}