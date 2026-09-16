package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student extends User {
    private String branch;
    private double cgpa;
    private int graduationYear;
    private Set<Skill> skills;
    private List<Project> projects;
    private List<Certification> certifications;

    public Student(String studentId, String name, String email,
                   String branch, double cgpa, int graduationYear) {
        super(studentId, name, email);
        this.branch = branch;
        this.cgpa = cgpa;
        this.graduationYear = graduationYear;
        this.skills = new HashSet<>();
        this.projects = new ArrayList<>();
        this.certifications = new ArrayList<>();
    }

    public String getBranch() {
        return branch;
    }

    public double getCgpa() {
        return cgpa;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void addCertification(Certification certification) {
        certifications.add(certification);
    }

    public void displayStudent() {
    System.out.println("\n===== STUDENT PROFILE =====");
    System.out.println("Student ID: " + getUserId());
    System.out.println("Name: " + getName());
    System.out.println("Email: " + getEmail());
    System.out.println("Branch: " + branch);
    System.out.println("CGPA: " + cgpa);
    System.out.println("Graduation Year: " + graduationYear);

    System.out.println("Skills: " + skills);

    System.out.println("Projects: " + projects);

    System.out.println("Certifications: " + certifications);
}
}
