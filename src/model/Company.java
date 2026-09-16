package model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String companyId;
    private String companyName;
    private String industry;
    private List<JobRole> jobRoles;

    public Company(String companyId, String companyName, String industry) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.industry = industry;
        this.jobRoles = new ArrayList<>();
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public List<JobRole> getJobRoles() {
        return jobRoles;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void addJobRole(JobRole jobRole) {
        jobRoles.add(jobRole);
    }

    @Override
    public String toString() {
        return companyName + " - " + industry;
    }
}