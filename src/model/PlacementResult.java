package model;

import java.util.HashSet;
import java.util.Set;

public class PlacementResult {
    private String studentId;
    private String companyId;
    private String jobId;
    private boolean eligible;
    private double cgpaScore;
    private double requiredSkillScore;
    private double preferredSkillScore;
    private double profileScore;
    private double overallMatchScore;
    private Set<String> missingSkills;

    public PlacementResult(String studentId, String companyId, String jobId) {
        this.studentId = studentId;
        this.companyId = companyId;
        this.jobId = jobId;
        this.missingSkills = new HashSet<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getJobId() {
        return jobId;
    }

    public boolean isEligible() {
        return eligible;
    }

    public double getCgpaScore() {
        return cgpaScore;
    }

    public double getRequiredSkillScore() {
        return requiredSkillScore;
    }

    public double getPreferredSkillScore() {
        return preferredSkillScore;
    }

    public double getProfileScore() {
        return profileScore;
    }

    public double getOverallMatchScore() {
        return overallMatchScore;
    }

    public Set<String> getMissingSkills() {
        return missingSkills;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public void setCgpaScore(double cgpaScore) {
        this.cgpaScore = cgpaScore;
    }

    public void setRequiredSkillScore(double requiredSkillScore) {
        this.requiredSkillScore = requiredSkillScore;
    }

    public void setPreferredSkillScore(double preferredSkillScore) {
        this.preferredSkillScore = preferredSkillScore;
    }

    public void setProfileScore(double profileScore) {
        this.profileScore = profileScore;
    }

    public void setOverallMatchScore(double overallMatchScore) {
        this.overallMatchScore = overallMatchScore;
    }

    public void addMissingSkill(String skill) {
        missingSkills.add(skill);
    }

    @Override
    public String toString() {
        return "Job: " + jobId +
                ", Eligible: " + eligible +
                ", Match Score: " + overallMatchScore +
                ", Missing Skills: " + missingSkills;
    }
}