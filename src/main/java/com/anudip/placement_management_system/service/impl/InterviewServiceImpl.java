package com.anudip.placement_management_system.service.impl;

import com.anudip.placement_management_system.dto.interview.InterviewRequest;
import com.anudip.placement_management_system.dto.interview.InterviewResponse;
import com.anudip.placement_management_system.entity.Application;
import com.anudip.placement_management_system.entity.Interview;
import com.anudip.placement_management_system.enums.InterviewResult;
import com.anudip.placement_management_system.repository.ApplicationRepository;
import com.anudip.placement_management_system.repository.InterviewRepository;
import com.anudip.placement_management_system.service.InterviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final ApplicationRepository applicationRepository;

    public InterviewServiceImpl(
            InterviewRepository interviewRepository,
            ApplicationRepository applicationRepository) {

        this.interviewRepository = interviewRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public InterviewResponse createInterview(
            InterviewRequest request) {

        Application application =
                applicationRepository.findById(
                                request.getApplicationId())
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        Interview interview = new Interview();

        interview.setApplication(application);
        interview.setInterviewDate(request.getInterviewDate());
        interview.setInterviewTime(request.getInterviewTime());
        interview.setMode(request.getMode());
        interview.setResult(InterviewResult.PENDING);

        Interview savedInterview =
                interviewRepository.save(interview);

        return mapToResponse(savedInterview);
    }

    @Override
    public InterviewResponse getInterviewById(Long id) {

        Interview interview =
                interviewRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Interview not found"));

        return mapToResponse(interview);
    }

    @Override
    public List<InterviewResponse> getAllInterviews() {

        return interviewRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public InterviewResponse updateInterview(
            Long id,
            InterviewRequest request) {

        Interview interview =
                interviewRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Interview not found"));

        interview.setInterviewDate(request.getInterviewDate());
        interview.setInterviewTime(request.getInterviewTime());
        interview.setMode(request.getMode());

        if (request.getResult() != null) {
            interview.setResult(request.getResult());
        }

        Interview updatedInterview =
                interviewRepository.save(interview);

        return mapToResponse(updatedInterview);
    }

    @Override
    public void updateInterviewResult(
            Long id,
            InterviewResult result) {

        Interview interview =
                interviewRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Interview not found"));

        interview.setResult(result);

        interviewRepository.save(interview);
    }

    private InterviewResponse mapToResponse(
            Interview interview) {

        InterviewResponse response = new InterviewResponse();

        response.setId(interview.getId());
        response.setInterviewDate(
                interview.getInterviewDate());
        response.setInterviewTime(
                interview.getInterviewTime());
        response.setMode(interview.getMode());
        response.setResult(interview.getResult());

        if (interview.getApplication() != null) {
            response.setApplicationId(
                    interview.getApplication().getId());
        }

        return response;
    }
}