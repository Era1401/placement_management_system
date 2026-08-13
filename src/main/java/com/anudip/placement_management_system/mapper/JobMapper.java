package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.job.JobRequest;
import com.anudip.placement_management_system.dto.job.JobResponse;
import com.anudip.placement_management_system.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public Job toEntity(JobRequest request) {

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setEligibilityCgpa(request.getEligibilityCgpa());

        return job;
    }

    public JobResponse toResponse(Job job) {

        JobResponse response = new JobResponse();

        response.setId(job.getId());
        response.setTitle(job.getTitle());
        response.setDescription(job.getDescription());
        response.setLocation(job.getLocation());
        response.setSalary(job.getSalary());
        response.setEligibilityCgpa(job.getEligibilityCgpa());
        response.setStatus(job.getStatus());

        if (job.getCompany() != null) {
            response.setCompanyId(
                    job.getCompany().getId()
            );

            response.setCompanyName(
                    job.getCompany().getName()
            );
        }

        return response;
    }

    public void updateEntity(
            Job job,
            JobRequest request) {

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setEligibilityCgpa(request.getEligibilityCgpa());
    }
}