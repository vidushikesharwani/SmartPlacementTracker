package io;

import model.Company;
import model.JobRole;
import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private final String studentFile = "data/students.txt";
    private final String companyFile = "data/companies.txt";

    public void saveStudents(List<Student> students) throws IOException {
        File file = new File(studentFile);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (Student student : students) {
                writer.write(
                        student.getUserId() + "|" +
                        student.getName() + "|" +
                        student.getEmail() + "|" +
                        student.getBranch() + "|" +
                        student.getCgpa() + "|" +
                        student.getGraduationYear()
                );

                writer.newLine();
            }
        }
    }

    public List<Student> loadStudents() throws IOException {

        List<Student> students = new ArrayList<>();
        File file = new File(studentFile);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length != 6) {
                    continue;
                }

                Student student = new Student(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        Double.parseDouble(data[4]),
                        Integer.parseInt(data[5])
                );

                students.add(student);
            }
        }

        return students;
    }

    public void saveCompanies(List<Company> companies) throws IOException {

        File file = new File(companyFile);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (Company company : companies) {

                for (JobRole jobRole : company.getJobRoles()) {

                    writer.write(
                            company.getCompanyId() + "|" +
                            company.getCompanyName() + "|" +
                            company.getIndustry() + "|" +
                            jobRole.getJobId() + "|" +
                            jobRole.getRoleName() + "|" +
                            jobRole.getMinimumCGPA() + "|" +
                            String.join(",", jobRole.getRequiredSkills()) + "|" +
                            String.join(",", jobRole.getPreferredSkills())
                    );

                    writer.newLine();
                }
            }
        }
    }

    public List<Company> loadCompanies() throws IOException {

        List<Company> companies = new ArrayList<>();
        File file = new File(companyFile);

        if (!file.exists()) {
            return companies;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length != 8) {
                    continue;
                }

                Company company = findCompany(companies, data[0]);

                if (company == null) {
                    company = new Company(
                            data[0],
                            data[1],
                            data[2]
                    );

                    companies.add(company);
                }

                JobRole jobRole = new JobRole(
                        data[3],
                        data[4],
                        Double.parseDouble(data[5])
                );

                if (!data[6].isEmpty()) {
                    String[] requiredSkills = data[6].split(",");

                    for (String skill : requiredSkills) {
                        jobRole.addRequiredSkill(skill);
                    }
                }

                if (!data[7].isEmpty()) {
                    String[] preferredSkills = data[7].split(",");

                    for (String skill : preferredSkills) {
                        jobRole.addPreferredSkill(skill);
                    }
                }

                company.addJobRole(jobRole);
            }
        }

        return companies;
    }

    private Company findCompany(List<Company> companies, String companyId) {

        for (Company company : companies) {

            if (company.getCompanyId().equalsIgnoreCase(companyId)) {
                return company;
            }
        }

        return null;
    }
}