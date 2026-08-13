package com.anudip.placement_management_system.entity;
import com.anudip.placement_management_system.enums.JobStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String location;

    private Double salary;

    private Double eligibilityCgpa;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Job() {
    }

    public Job(String title, String description, String location,
               Double salary, Double eligibilityCgpa,
               JobStatus status, Company company) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.eligibilityCgpa = eligibilityCgpa;
        this.status = status;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getEligibilityCgpa() {
        return eligibilityCgpa;
    }

    public void setEligibilityCgpa(Double eligibilityCgpa) {
        this.eligibilityCgpa = eligibilityCgpa;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}