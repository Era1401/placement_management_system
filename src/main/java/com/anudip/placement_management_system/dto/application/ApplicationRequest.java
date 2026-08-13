package com.anudip.placement_management_system.dto.application;

import com.anudip.placement_management_system.enums.ApplicationStatus;

public class ApplicationRequest {

    private Long studentId;
    private Long jobId;
    private ApplicationStatus status;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}