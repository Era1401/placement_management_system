package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.job.JobRequest;
import com.anudip.placement_management_system.dto.job.JobResponse;

import java.util.List;

public interface JobService {

    JobResponse createJob(JobRequest request);

    JobResponse getJobById(Long id);

    List<JobResponse> getAllJobs();

    JobResponse updateJob(Long id, JobRequest request);

    void deactivateJob(Long id);


}