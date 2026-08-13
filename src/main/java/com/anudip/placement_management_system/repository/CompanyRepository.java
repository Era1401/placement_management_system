package com.anudip.placement_management_system.repository;

import com.anudip.placement_management_system.entity.Company;
import com.anudip.placement_management_system.enums.CompanyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String name);

    List<Company> findByStatus(CompanyStatus status);

    List<Company> findByIndustry(String industry);
}