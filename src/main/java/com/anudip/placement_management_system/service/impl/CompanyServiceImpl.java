package com.anudip.placement_management_system.service.impl;


import com.anudip.placement_management_system.dto.company.CompanyRequest;
import com.anudip.placement_management_system.dto.company.CompanyResponse;
import com.anudip.placement_management_system.entity.Company;
import com.anudip.placement_management_system.enums.CompanyStatus;
import com.anudip.placement_management_system.repository.CompanyRepository;
import com.anudip.placement_management_system.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyResponse createCompany(CompanyRequest request) {

        Company company = new Company();

        company.setName(request.getName());
        company.setLocation(request.getLocation());
        company.setIndustry(request.getIndustry());
        company.setContactEmail(request.getContactEmail());
        company.setContactPhone(request.getContactPhone());
        company.setStatus(CompanyStatus.ACTIVE);

        Company savedCompany = companyRepository.save(company);

        return mapToResponse(savedCompany);
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return mapToResponse(company);
    }

    @Override
    public List<CompanyResponse> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest request) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setName(request.getName());
        company.setLocation(request.getLocation());
        company.setIndustry(request.getIndustry());
        company.setContactEmail(request.getContactEmail());
        company.setContactPhone(request.getContactPhone());

        Company updatedCompany = companyRepository.save(company);

        return mapToResponse(updatedCompany);
    }

    @Override
    public void deactivateCompany(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setStatus(CompanyStatus.INACTIVE);

        companyRepository.save(company);
    }

    private CompanyResponse mapToResponse(Company company) {

        CompanyResponse response = new CompanyResponse();

        response.setId(company.getId());
        response.setName(company.getName());
        response.setLocation(company.getLocation());
        response.setIndustry(company.getIndustry());
        response.setContactEmail(company.getContactEmail());
        response.setContactPhone(company.getContactPhone());
        response.setStatus(company.getStatus());

        return response;
    }
}