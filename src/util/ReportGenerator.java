package util;

import model.Company;
import model.Student;

import java.util.List;

public class ReportGenerator {

    public void printStudentReport(List<Student> students) {

        System.out.println("===== STUDENT REPORT =====");
        System.out.println("Total Students: " + students.size());

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        double totalCgpa = 0.0;

        for (Student student : students) {
            totalCgpa += student.getCgpa();
        }

        double averageCgpa = totalCgpa / students.size();

        System.out.printf("Average CGPA: %.2f%n", averageCgpa);
    }

    public void printCompanyReport(List<Company> companies) {

        System.out.println("===== COMPANY REPORT =====");
        System.out.println("Total Companies: " + companies.size());

        int totalJobs = 0;

        for (Company company : companies) {
            totalJobs += company.getJobRoles().size();
        }

        System.out.println("Total Job Roles: " + totalJobs);
    }
}