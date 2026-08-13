package com.anudip.placement_management_system.controller;

import com.anudip.placement_management_system.dto.interview.InterviewRequest;
import com.anudip.placement_management_system.dto.interview.InterviewResponse;
import com.anudip.placement_management_system.enums.InterviewResult;
import com.anudip.placement_management_system.service.InterviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(
            InterviewService interviewService) {

        this.interviewService = interviewService;
    }

    @PostMapping
    public ResponseEntity<InterviewResponse> createInterview(
            @RequestBody InterviewRequest request) {

        InterviewResponse response =
                interviewService.createInterview(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewResponse> getInterviewById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                interviewService.getInterviewById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<InterviewResponse>>
    getAllInterviews() {

        return ResponseEntity.ok(
                interviewService.getAllInterviews()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewResponse> updateInterview(
            @PathVariable Long id,
            @RequestBody InterviewRequest request) {

        return ResponseEntity.ok(
                interviewService.updateInterview(id, request)
        );
    }

    @PatchMapping("/{id}/result")
    public ResponseEntity<Void> updateInterviewResult(
            @PathVariable Long id,
            @RequestParam InterviewResult result) {

        interviewService.updateInterviewResult(id, result);

        return ResponseEntity.noContent().build();
    }
}