package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.student.StudentRequest;
import com.anudip.placement_management_system.dto.student.StudentResponse;
import com.anudip.placement_management_system.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequest request) {

        Student student = new Student();

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCgpa(request.getCgpa());
        student.setBranch(request.getBranch());
        student.setResume(request.getResume());

        return student;
    }

    public StudentResponse toResponse(Student student) {

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

    public void updateEntity(
            Student student,
            StudentRequest request) {

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCgpa(request.getCgpa());
        student.setBranch(request.getBranch());
        student.setResume(request.getResume());
    }
}