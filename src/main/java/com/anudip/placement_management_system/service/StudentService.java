package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.student.StudentRequest;
import com.anudip.placement_management_system.dto.student.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);

    StudentResponse getStudentById(Long id);

    List<StudentResponse> getAllStudents();

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deactivateStudent(Long id);
}