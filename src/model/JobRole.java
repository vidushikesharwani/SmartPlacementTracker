package model;

import java.util.HashSet;
import java.util.Set;

public class JobRole {
    private String jobId;
    private String roleName;
    private double minimumCGPA;
    private Set<String> requiredSkills;
    private Set<String> preferredSkills;

    public JobRole(String jobId, String roleName, double minimumCGPA) {
        this.jobId = jobId;
        this.roleName = roleName;
        this.minimumCGPA = minimumCGPA;
        this.requiredSkills = new HashSet<>();
        this.preferredSkills = new HashSet<>();
    }

    public String getJobId() {
        return jobId;
    }

    public String getRoleName() {
        return roleName;
    }

    public double getMinimumCGPA() {
        return minimumCGPA;
    }

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public Set<String> getPreferredSkills() {
        return preferredSkills;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setMinimumCGPA(double minimumCGPA) {
        this.minimumCGPA = minimumCGPA;
    }

    public void addRequiredSkill(String skill) {
        requiredSkills.add(skill);
    }

    public void addPreferredSkill(String skill) {
        preferredSkills.add(skill);
    }

    @Override
    public String toString() {
        return roleName + " (Min CGPA: " + minimumCGPA + ")";
    }
}