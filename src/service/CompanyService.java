package service;

import exception.InvalidCompanyException;
import model.Company;
import model.JobRole;
import util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class CompanyService {

    private List<Company> companies;

    public CompanyService() {
        companies = new ArrayList<>();
    }

    public void addCompany(Company company)
            throws InvalidCompanyException {

        if (company == null) {
            throw new InvalidCompanyException(
                    "Company cannot be null."
            );
        }

        if (!InputValidator.isValidId(company.getCompanyId())) {
            throw new InvalidCompanyException(
                    "Company ID cannot be empty."
            );
        }

        if (!InputValidator.isValidName(company.getCompanyName())) {
            throw new InvalidCompanyException(
                    "Company name cannot be empty."
            );
        }

        if (!InputValidator.isValidName(company.getIndustry())) {
            throw new InvalidCompanyException(
                    "Industry cannot be empty."
            );
        }

        if (findCompanyById(company.getCompanyId()) != null) {
            throw new InvalidCompanyException(
                    "Company ID already exists."
            );
        }

        companies.add(company);
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public Company findCompanyById(String companyId) {

        for (Company company : companies) {

            if (company.getCompanyId()
                    .equalsIgnoreCase(companyId)) {

                return company;
            }
        }

        return null;
    }

    public boolean updateCompany(
            String companyId,
            String companyName,
            String industry)
            throws InvalidCompanyException {

        Company company = findCompanyById(companyId);

        if (company == null) {
            throw new InvalidCompanyException(
                    "Company not found."
            );
        }

        if (!InputValidator.isValidName(companyName)) {
            throw new InvalidCompanyException(
                    "Company name cannot be empty."
            );
        }

        if (!InputValidator.isValidName(industry)) {
            throw new InvalidCompanyException(
                    "Industry cannot be empty."
            );
        }

        company.setCompanyName(companyName);
        company.setIndustry(industry);

        return true;
    }

    public boolean removeCompany(String companyId)
            throws InvalidCompanyException {

        Company company = findCompanyById(companyId);

        if (company == null) {
            throw new InvalidCompanyException(
                    "Company not found."
            );
        }

        companies.remove(company);

        return true;
    }

    public boolean addJobRole(
            String companyId,
            JobRole jobRole)
            throws InvalidCompanyException {

        Company company = findCompanyById(companyId);

        if (company == null) {
            throw new InvalidCompanyException(
                    "Company not found."
            );
        }

        if (jobRole == null) {
            throw new InvalidCompanyException(
                    "Job role cannot be null."
            );
        }

        if (!InputValidator.isValidId(jobRole.getJobId())) {
            throw new InvalidCompanyException(
                    "Job ID cannot be empty."
            );
        }

        if (!InputValidator.isValidName(jobRole.getRoleName())) {
            throw new InvalidCompanyException(
                    "Job role name cannot be empty."
            );
        }

        if (jobRole.getMinimumCGPA() < 0
                || jobRole.getMinimumCGPA() > 10) {

            throw new InvalidCompanyException(
                    "Minimum CGPA must be between 0 and 10."
            );
        }

        if (findJobRoleById(jobRole.getJobId()) != null) {
            throw new InvalidCompanyException(
                    "Job ID already exists."
            );
        }

        company.addJobRole(jobRole);

        return true;
    }
    public void loadCompanies(List<Company> loadedCompanies) {
    companies.clear();
    companies.addAll(loadedCompanies);
}
    public JobRole findJobRoleById(String jobId) {

        for (Company company : companies) {

            for (JobRole jobRole : company.getJobRoles()) {

                if (jobRole.getJobId()
                        .equalsIgnoreCase(jobId)) {

                    return jobRole;
                }
            }
        }

        return null;
    }

    public int getCompanyCount() {
        return companies.size();
    }

    public int getJobRoleCount() {

        int count = 0;

        for (Company company : companies) {
            count += company.getJobRoles().size();
        }

        return count;
    }
    
}
