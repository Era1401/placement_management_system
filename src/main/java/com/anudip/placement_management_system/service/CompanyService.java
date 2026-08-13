package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.company.CompanyRequest;
import com.anudip.placement_management_system.dto.company.CompanyResponse;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(CompanyRequest request);

    CompanyResponse getCompanyById(Long id);

    List<CompanyResponse> getAllCompanies();

    CompanyResponse updateCompany(Long id, CompanyRequest request);

    void deactivateCompany(Long id);
}