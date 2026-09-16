package service;
import model.Assessment;
import model.JobRole;
import model.PlacementResult;
import model.Skill;
import model.Student;

import java.util.HashSet;
import java.util.Set;

public class MatchingEngine {

    private final EligibilityEngine eligibilityEngine;

    public MatchingEngine() {
        this.eligibilityEngine = new EligibilityEngine();
    }

    public PlacementResult calculateMatch(
        Student student,
        JobRole jobRole,
        Assessment assessment,
        String companyId) {

        PlacementResult result = new PlacementResult(
                student.getUserId(),
                companyId,
                jobRole.getJobId()
        );

        boolean eligible = eligibilityEngine.isEligible(
                student,
                jobRole,
                assessment
        );

        result.setEligible(eligible);

        Set<String> missingSkills =
                eligibilityEngine.getMissingSkills(student, jobRole);

        for (String skill : missingSkills) {
            result.addMissingSkill(skill);
        }

        if (!eligible) {
            return result;
        }

        double cgpaScore = calculateCgpaScore(student);
        double requiredSkillScore =
                calculateRequiredSkillScore(student, jobRole);
        double preferredSkillScore =
                calculatePreferredSkillScore(student, jobRole);

        double skillScore =
                (requiredSkillScore * 0.80)
                + (preferredSkillScore * 0.20);

        double projectScore = calculateProjectScore(student);
        double certificationScore =
                calculateCertificationScore(student);

        double profileScore =
                (projectScore * 0.50)
                + (certificationScore * 0.50);

        double overallScore =
                (cgpaScore * 0.30)
                + (skillScore * 0.50)
                + (profileScore * 0.20);

        result.setCgpaScore(cgpaScore);
        result.setRequiredSkillScore(requiredSkillScore);
        result.setPreferredSkillScore(preferredSkillScore);
        result.setProfileScore(profileScore);
        result.setOverallMatchScore(overallScore);

        return result;
    }

    private double calculateCgpaScore(Student student) {
        return (student.getCgpa() / 10.0) * 100.0;
    }

    private double calculateRequiredSkillScore(
            Student student,
            JobRole jobRole) {

        Set<String> studentSkills = getStudentSkillNames(student);
        Set<String> requiredSkills = jobRole.getRequiredSkills();

        if (requiredSkills.isEmpty()) {
            return 100.0;
        }

        int matchedSkills = 0;

        for (String skill : requiredSkills) {
            if (studentSkills.contains(skill)) {
                matchedSkills++;
            }
        }

        return ((double) matchedSkills / requiredSkills.size()) * 100.0;
    }

    private double calculatePreferredSkillScore(
            Student student,
            JobRole jobRole) {

        Set<String> studentSkills = getStudentSkillNames(student);
        Set<String> preferredSkills = jobRole.getPreferredSkills();

        if (preferredSkills.isEmpty()) {
            return 100.0;
        }

        int matchedSkills = 0;

        for (String skill : preferredSkills) {
            if (studentSkills.contains(skill)) {
                matchedSkills++;
            }
        }

        return ((double) matchedSkills / preferredSkills.size()) * 100.0;
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

    private Set<String> getStudentSkillNames(Student student) {
        Set<String> skillNames = new HashSet<>();

        for (Skill skill : student.getSkills()) {
            skillNames.add(skill.getSkillName());
        }

        return skillNames;
    }

    public String getMatchCategory(double score) {
        if (score >= 90) {
            return "Excellent Match";
        } else if (score >= 75) {
            return "Strong Match";
        } else if (score >= 60) {
            return "Moderate Match";
        } else if (score >= 40) {
            return "Weak Match";
        } else {
            return "Low Match";
        }
    }
}