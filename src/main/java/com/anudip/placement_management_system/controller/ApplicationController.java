package com.anudip.placement_management_system.controller;

import com.anudip.placement_management_system.dto.application.ApplicationRequest;
import com.anudip.placement_management_system.dto.application.ApplicationResponse;
import com.anudip.placement_management_system.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(
            ApplicationService applicationService) {

        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(
            @RequestBody ApplicationRequest request) {

        ApplicationResponse response =
                applicationService.createApplication(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                applicationService.getApplicationById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>>
    getAllApplications() {

        return ResponseEntity.ok(
                applicationService.getAllApplications()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse>
    updateApplication(
            @PathVariable Long id,
            @RequestBody ApplicationRequest request) {

        return ResponseEntity.ok(
                applicationService.updateApplication(id, request)
        );
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<Void> rejectApplication(
            @PathVariable Long id) {

        applicationService.rejectApplication(id);

        return ResponseEntity.noContent().build();
    }
}