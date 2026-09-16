package model;

import java.util.ArrayList;
import java.util.List;

public class ReadinessReport {
    private String studentId;
    private double cgpaScore;
    private double skillScore;
    private double projectScore;
    private double certificationScore;
    private double assessmentScore;
    private double overallScore;
    private List<String> strengths;
    private List<String> improvementAreas;

    public ReadinessReport(String studentId) {
        this.studentId = studentId;
        this.strengths = new ArrayList<>();
        this.improvementAreas = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public double getCgpaScore() {
        return cgpaScore;
    }

    public double getSkillScore() {
        return skillScore;
    }

    public double getProjectScore() {
        return projectScore;
    }

    public double getCertificationScore() {
        return certificationScore;
    }

    public double getAssessmentScore() {
        return assessmentScore;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public List<String> getImprovementAreas() {
        return improvementAreas;
    }

    public void setCgpaScore(double cgpaScore) {
        this.cgpaScore = cgpaScore;
    }

    public void setSkillScore(double skillScore) {
        this.skillScore = skillScore;
    }

    public void setProjectScore(double projectScore) {
        this.projectScore = projectScore;
    }

    public void setCertificationScore(double certificationScore) {
        this.certificationScore = certificationScore;
    }

    public void setAssessmentScore(double assessmentScore) {
        this.assessmentScore = assessmentScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public void addStrength(String strength) {
        strengths.add(strength);
    }

    public void addImprovementArea(String area) {
        improvementAreas.add(area);
    }

    @Override
    public String toString() {
        return "Student: " + studentId +
                ", Readiness Score: " + overallScore;
    }
}