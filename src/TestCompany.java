import model.Company;
import model.JobRole;

public class TestCompany {
    public static void main(String[] args) {
        Company company = new Company(
                "C101",
                "TechCorp",
                "Technology"
        );

        JobRole developer = new JobRole(
                "J101",
                "Software Developer",
                7.5
        );

        developer.addRequiredSkill("Java");
        developer.addRequiredSkill("DSA");
        developer.addRequiredSkill("SQL");

        developer.addPreferredSkill("Git");
        developer.addPreferredSkill("Spring");

        company.addJobRole(developer);

        System.out.println("Company: " + company);
        System.out.println("Job Roles:");

        for (JobRole job : company.getJobRoles()) {
            System.out.println(job);
            System.out.println("Required Skills: " + job.getRequiredSkills());
            System.out.println("Preferred Skills: " + job.getPreferredSkills());
        }
    }
}