package com.anudip.placement_management_system.dto.placement;

import com.anudip.placement_management_system.enums.PlacementStatus;

import java.time.LocalDate;

public class PlacementRequest {

 private Long studentId;
 private Long companyId;
 private Long jobId;

 private Double packageAmount;
 private LocalDate placementDate;

 private PlacementStatus status;

 public Long getStudentId() {
  return studentId;
 }

 public void setStudentId(Long studentId) {
  this.studentId = studentId;
 }

 public Long getCompanyId() {
  return companyId;
 }

 public void setCompanyId(Long companyId) {
  this.companyId = companyId;
 }

 public Long getJobId() {
  return jobId;
 }

 public void setJobId(Long jobId) {
  this.jobId = jobId;
 }

 public Double getPackageAmount() {
  return packageAmount;
 }

 public void setPackageAmount(Double packageAmount) {
  this.packageAmount = packageAmount;
 }

 public LocalDate getPlacementDate() {
  return placementDate;
 }

 public void setPlacementDate(LocalDate placementDate) {
  this.placementDate = placementDate;
 }

 public PlacementStatus getStatus() {
  return status;
 }

 public void setStatus(PlacementStatus status) {
  this.status = status;
 }
}