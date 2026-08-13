package com.anudip.placement_management_system.repository;

import com.anudip.placement_management_system.entity.Interview;
import com.anudip.placement_management_system.enums.InterviewResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InterviewRepository
        extends JpaRepository<Interview, Long> {

    List<Interview> findByApplicationId(Long applicationId);

    List<Interview> findByResult(InterviewResult result);

    List<Interview> findByInterviewDate(LocalDate interviewDate);

    List<Interview> findByApplicationIdAndResult(
            Long applicationId,
            InterviewResult result);
}