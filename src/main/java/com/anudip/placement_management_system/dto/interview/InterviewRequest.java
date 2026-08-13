package com.anudip.placement_management_system.dto.interview;

import com.anudip.placement_management_system.enums.InterviewResult;

import java.time.LocalDate;
import java.time.LocalTime;

public class InterviewRequest {

 private Long applicationId;
 private LocalDate interviewDate;
 private LocalTime interviewTime;
 private String mode;
 private InterviewResult result;

 public Long getApplicationId() {
  return applicationId;
 }

 public void setApplicationId(Long applicationId) {
  this.applicationId = applicationId;
 }

 public LocalDate getInterviewDate() {
  return interviewDate;
 }

 public void setInterviewDate(LocalDate interviewDate) {
  this.interviewDate = interviewDate;
 }

 public LocalTime getInterviewTime() {
  return interviewTime;
 }

 public void setInterviewTime(LocalTime interviewTime) {
  this.interviewTime = interviewTime;
 }

 public String getMode() {
  return mode;
 }

 public void setMode(String mode) {
  this.mode = mode;
 }

 public InterviewResult getResult() {
  return result;
 }

 public void setResult(InterviewResult result) {
  this.result = result;
 }
}