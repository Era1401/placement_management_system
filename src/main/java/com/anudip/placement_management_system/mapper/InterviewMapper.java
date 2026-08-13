package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.interview.InterviewRequest;
import com.anudip.placement_management_system.dto.interview.InterviewResponse;
import com.anudip.placement_management_system.entity.Interview;
import org.springframework.stereotype.Component;

@Component
public class InterviewMapper {

    public Interview toEntity(
            InterviewRequest request) {

        Interview interview = new Interview();

        interview.setInterviewDate(
                request.getInterviewDate()
        );

        interview.setInterviewTime(
                request.getInterviewTime()
        );

        interview.setMode(
                request.getMode()
        );

        interview.setResult(
                request.getResult()
        );

        return interview;
    }

    public InterviewResponse toResponse(
            Interview interview) {

        InterviewResponse response =
                new InterviewResponse();

        response.setId(interview.getId());

        response.setInterviewDate(
                interview.getInterviewDate()
        );

        response.setInterviewTime(
                interview.getInterviewTime()
        );

        response.setMode(
                interview.getMode()
        );

        response.setResult(
                interview.getResult()
        );

        if (interview.getApplication() != null) {

            response.setApplicationId(
                    interview.getApplication().getId()
            );
        }

        return response;
    }

    public void updateEntity(
            Interview interview,
            InterviewRequest request) {

        interview.setInterviewDate(
                request.getInterviewDate()
        );

        interview.setInterviewTime(
                request.getInterviewTime()
        );

        interview.setMode(
                request.getMode()
        );

        if (request.getResult() != null) {

            interview.setResult(
                    request.getResult()
            );
        }
    }
}