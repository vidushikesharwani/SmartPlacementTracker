import model.Company;
import model.JobRole;
import model.Student;
import util.ReportGenerator;

import java.util.ArrayList;
import java.util.List;

public class TestReportGenerator {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        students.add(
                new Student(
                        "S101",
                        "Vidushi",
                        "vidushi@example.com",
                        "CSE",
                        8.7,
                        2027
                )
        );

        students.add(
                new Student(
                        "S102",
                        "Aditi",
                        "aditi@example.com",
                        "CSE",
                        8.2,
                        2027
                )
        );

        List<Company> companies = new ArrayList<>();

        Company company = new Company(
                "C101",
                "Tech Solutions",
                "Technology"
        );

        JobRole job = new JobRole(
                "J101",
                "Software Developer",
                7.5
        );

        company.addJobRole(job);
        companies.add(company);

        ReportGenerator generator =
                new ReportGenerator();

        generator.printStudentReport(students);

        System.out.println();

        generator.printCompanyReport(companies);
    }
}