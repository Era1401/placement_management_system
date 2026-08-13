package com.anudip.placement_management_system.controller;

import com.anudip.placement_management_system.dto.dashboard.DashboardResponse;
import com.anudip.placement_management_system.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboardStatistics() {

        return ResponseEntity.ok(
                dashboardService.getDashboardStatistics()
        );
    }
}