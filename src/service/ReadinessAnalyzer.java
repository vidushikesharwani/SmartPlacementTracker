package service;

import model.Assessment;
import model.ReadinessReport;
import model.Skill;
import model.Student;

public class ReadinessAnalyzer {

    public ReadinessReport analyze(Student student, Assessment assessment) {

        ReadinessReport report =
                new ReadinessReport(student.getUserId());

        double cgpaScore = calculateCgpaScore(student);
        double skillScore = calculateSkillScore(student);
        double projectScore = calculateProjectScore(student);
        double certificationScore = calculateCertificationScore(student);
        double assessmentScore = calculateAssessmentScore(assessment);

        double overallScore =
                (cgpaScore * 0.30)
                + (skillScore * 0.30)
                + (projectScore * 0.15)
                + (certificationScore * 0.10)
                + (assessmentScore * 0.15);

        report.setCgpaScore(cgpaScore);
        report.setSkillScore(skillScore);
        report.setProjectScore(projectScore);
        report.setCertificationScore(certificationScore);
        report.setAssessmentScore(assessmentScore);
        report.setOverallScore(overallScore);

        addStrengths(report);
        addImprovementAreas(report);

        return report;
    }

    private double calculateCgpaScore(Student student) {
        return (student.getCgpa() / 10.0) * 100.0;
    }

    private double calculateSkillScore(Student student) {

        if (student.getSkills().isEmpty()) {
            return 0.0;
        }

        double totalScore = 0.0;

        for (Skill skill : student.getSkills()) {

            switch (skill.getProficiencyLevel()) {
                case BEGINNER:
                    totalScore += 40;
                    break;

                case INTERMEDIATE:
                    totalScore += 60;
                    break;

                case ADVANCED:
                    totalScore += 80;
                    break;

                case EXPERT:
                    totalScore += 100;
                    break;
            }
        }

        return totalScore / student.getSkills().size();
    }

    private double calculateProjectScore(Student student) {

        int count = student.getProjects().size();

        if (count == 0) {
            return 0.0;
        } else if (count == 1) {
            return 50.0;
        } else if (count == 2) {
            return 80.0;
        } else {
            return 100.0;
        }
    }

    private double calculateCertificationScore(Student student) {

        int count = student.getCertifications().size();

        if (count == 0) {
            return 0.0;
        } else if (count == 1) {
            return 60.0;
        } else if (count == 2) {
            return 80.0;
        } else {
            return 100.0;
        }
    }

    private double calculateAssessmentScore(Assessment assessment) {

        if (assessment == null) {
            return 0.0;
        }

        return assessment.getAverageScore();
    }

    private void addStrengths(ReadinessReport report) {

        if (report.getCgpaScore() >= 80) {
            report.addStrength("Good academic performance");
        }

        if (report.getSkillScore() >= 80) {
            report.addStrength("Strong technical skills");
        }

        if (report.getProjectScore() >= 80) {
            report.addStrength("Good project experience");
        }

        if (report.getCertificationScore() >= 80) {
            report.addStrength("Good certification profile");
        }

        if (report.getAssessmentScore() >= 80) {
            report.addStrength("Strong assessment performance");
        }
    }

    private void addImprovementAreas(ReadinessReport report) {

        if (report.getCgpaScore() < 60) {
            report.addImprovementArea("Improve academic performance");
        }

        if (report.getSkillScore() < 60) {
            report.addImprovementArea("Improve technical skills");
        }

        if (report.getProjectScore() < 60) {
            report.addImprovementArea("Build more projects");
        }

        if (report.getCertificationScore() < 60) {
            report.addImprovementArea("Consider completing relevant certifications");
        }

        if (report.getAssessmentScore() < 60) {
            report.addImprovementArea("Improve assessment performance");
        }
    }
}