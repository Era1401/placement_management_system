package com.anudip.placement_management_system.repository;

import com.anudip.placement_management_system.entity.Application;
import com.anudip.placement_management_system.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    List<Application> findByStudentId(Long studentId);

    List<Application> findByJobId(Long jobId);

    List<Application> findByStatus(ApplicationStatus status);

    List<Application> findByStudentIdAndStatus(
            Long studentId,
            ApplicationStatus status);

    List<Application> findByJobIdAndStatus(
            Long jobId,
            ApplicationStatus status);
}