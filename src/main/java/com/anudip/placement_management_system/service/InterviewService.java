package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.interview.InterviewRequest;
import com.anudip.placement_management_system.dto.interview.InterviewResponse;
import com.anudip.placement_management_system.enums.InterviewResult;

import java.util.List;

public interface InterviewService {

    InterviewResponse createInterview(InterviewRequest request);

    InterviewResponse getInterviewById(Long id);

    List<InterviewResponse> getAllInterviews();

    InterviewResponse updateInterview(
            Long id,
            InterviewRequest request
    );

    void updateInterviewResult(
            Long id,
            InterviewResult result);
}
