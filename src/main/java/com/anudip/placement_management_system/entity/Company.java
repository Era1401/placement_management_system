package com.anudip.placement_management_system.entity;
import com.anudip.placement_management_system.enums.CompanyStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String location;

    private String industry;

    private String contactEmail;

    private String contactPhone;

    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

    public Company() {
    }

    public Company(String name, String location, String industry,
                   String contactEmail, String contactPhone,
                   CompanyStatus status) {
        this.name = name;
        this.location = location;
        this.industry = industry;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }
}