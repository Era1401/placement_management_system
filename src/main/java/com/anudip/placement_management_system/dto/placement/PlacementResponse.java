package com.anudip.placement_management_system.dto.placement;

import com.anudip.placement_management_system.enums.PlacementStatus;

import java.time.LocalDate;

public class PlacementResponse {

 private Long id;

 private Long studentId;
 private String studentName;

 private Long companyId;
 private String companyName;

 private Long jobId;
 private String jobTitle;

 private Double packageAmount;
 private LocalDate placementDate;

 private PlacementStatus status;

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public Long getStudentId() {
  return studentId;
 }

 public void setStudentId(Long studentId) {
  this.studentId = studentId;
 }

 public String getStudentName() {
  return studentName;
 }

 public void setStudentName(String studentName) {
  this.studentName = studentName;
 }

 public Long getCompanyId() {
  return companyId;
 }

 public void setCompanyId(Long companyId) {
  this.companyId = companyId;
 }

 public String getCompanyName() {
  return companyName;
 }

 public void setCompanyName(String companyName) {
  this.companyName = companyName;
 }

 public Long getJobId() {
  return jobId;
 }

 public void setJobId(Long jobId) {
  this.jobId = jobId;
 }

 public String getJobTitle() {
  return jobTitle;
 }

 public void setJobTitle(String jobTitle) {
  this.jobTitle = jobTitle;
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