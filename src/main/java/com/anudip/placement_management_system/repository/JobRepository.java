package com.anudip.placement_management_system.repository;

import com.anudip.placement_management_system.entity.Job;
import com.anudip.placement_management_system.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByStatus(JobStatus status);

    List<Job> findByCompanyId(Long companyId);

    List<Job> findByEligibilityCgpaLessThanEqual(Double cgpa);

    List<Job> findByLocation(String location);
}