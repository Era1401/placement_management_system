package com.anudip.placement_management_system.repository;

import com.anudip.placement_management_system.entity.Student;
import com.anudip.placement_management_system.enums.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    List<Student> findByStatus(StudentStatus status);

    List<Student> findByBranch(String branch);

    List<Student> findByCgpaGreaterThanEqual(Double cgpa);
}