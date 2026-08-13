package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.company.CompanyRequest;
import com.anudip.placement_management_system.dto.company.CompanyResponse;
import com.anudip.placement_management_system.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequest request) {

        Company company = new Company();

        company.setName(request.getName());
        company.setLocation(request.getLocation());
        company.setIndustry(request.getIndustry());
        company.setContactEmail(request.getContactEmail());
        company.setContactPhone(request.getContactPhone());

        return company;
    }

    public CompanyResponse toResponse(Company company) {

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

    public void updateEntity(
            Company company,
            CompanyRequest request) {

        company.setName(request.getName());
        company.setLocation(request.getLocation());
        company.setIndustry(request.getIndustry());
        company.setContactEmail(request.getContactEmail());
        company.setContactPhone(request.getContactPhone());
    }
}