import exception.InvalidCompanyException;
import model.Company;
import model.JobRole;
import service.CompanyService;

public class TestCompanyService {

    public static void main(String[] args) {

        CompanyService service = new CompanyService();

        try {

            Company company1 = new Company(
                    "C101",
                    "Tech Solutions",
                    "Information Technology"
            );

            Company company2 = new Company(
                    "C102",
                    "Data Systems",
                    "Software"
            );

            JobRole job1 = new JobRole(
                    "J101",
                    "Software Developer",
                    7.5
            );

            job1.addRequiredSkill("Java");
            job1.addRequiredSkill("DSA");
            job1.addPreferredSkill("Git");

            JobRole job2 = new JobRole(
                    "J102",
                    "Data Analyst",
                    7.0
            );

            job2.addRequiredSkill("SQL");
            job2.addRequiredSkill("Python");

            service.addCompany(company1);
            service.addCompany(company2);

            service.addJobRole("C101", job1);
            service.addJobRole("C102", job2);

            System.out.println(
                    "Total Companies: "
                    + service.getCompanyCount()
            );

            System.out.println(
                    "Total Job Roles: "
                    + service.getJobRoleCount()
            );

            Company found =
                    service.findCompanyById("C101");

            if (found != null) {
                System.out.println("\nCompany Found:");
                System.out.println(found);
                System.out.println(
                        "Job Roles: "
                        + found.getJobRoles()
                );
            }

            JobRole foundJob =
                    service.findJobRoleById("J102");

            if (foundJob != null) {
                System.out.println("\nJob Found:");
                System.out.println(foundJob);
            }

            service.updateCompany(
                    "C101",
                    "Tech Solutions Pvt Ltd",
                    "Technology"
            );

            System.out.println(
                    "\nCompany Updated: "
                    + service.findCompanyById("C101")
            );

            service.removeCompany("C102");

            System.out.println(
                    "Companies Remaining: "
                    + service.getCompanyCount()
            );

        } catch (InvalidCompanyException e) {

            System.out.println(
                    "Company Error: " + e.getMessage()
            );
        }
    }
}