package com.anudip.placement_management_system.service.impl;

import com.anudip.placement_management_system.dto.student.StudentRequest;
import com.anudip.placement_management_system.dto.student.StudentResponse;
import com.anudip.placement_management_system.entity.Student;
import com.anudip.placement_management_system.enums.StudentStatus;
import com.anudip.placement_management_system.repository.StudentRepository;
import com.anudip.placement_management_system.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

 private final StudentRepository studentRepository;

 public StudentServiceImpl(StudentRepository studentRepository) {
  this.studentRepository = studentRepository;
 }

 @Override
 public StudentResponse createStudent(StudentRequest request) {

  Student student = new Student();

  student.setName(request.getName());
  student.setEmail(request.getEmail());
  student.setPhone(request.getPhone());
  student.setCgpa(request.getCgpa());
  student.setBranch(request.getBranch());
  student.setResume(request.getResume());
  student.setStatus(StudentStatus.ACTIVE);

  Student savedStudent = studentRepository.save(student);

  return mapToResponse(savedStudent);
 }

 @Override
 public StudentResponse getStudentById(Long id) {

  Student student = studentRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Student not found"));

  return mapToResponse(student);
 }

 @Override
 public List<StudentResponse> getAllStudents() {

  return studentRepository.findAll()
          .stream()
          .map(this::mapToResponse)
          .toList();
 }

 @Override
 public StudentResponse updateStudent(Long id, StudentRequest request) {

  Student student = studentRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Student not found"));

  student.setName(request.getName());
  student.setEmail(request.getEmail());
  student.setPhone(request.getPhone());
  student.setCgpa(request.getCgpa());
  student.setBranch(request.getBranch());
  student.setResume(request.getResume());

  Student updatedStudent = studentRepository.save(student);

  return mapToResponse(updatedStudent);
 }

 @Override
 public void deactivateStudent(Long id) {

  Student student = studentRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Student not found"));

  student.setStatus(StudentStatus.INACTIVE);

  studentRepository.save(student);
 }

 private StudentResponse mapToResponse(Student student) {

  StudentResponse response = new StudentResponse();

  response.setId(student.getId());
  response.setName(student.getName());
  response.setEmail(student.getEmail());
  response.setPhone(student.getPhone());
  response.setCgpa(student.getCgpa());
  response.setBranch(student.getBranch());
  response.setResume(student.getResume());
  response.setStatus(student.getStatus());

  return response;
 }
}