package service;

import model.Assessment;
import model.JobRole;
import model.Skill;
import model.Student;

import java.util.HashSet;
import java.util.Set;

public class EligibilityEngine {

    public boolean isEligible(Student student, JobRole jobRole, Assessment assessment) {
        if (student.getCgpa() < jobRole.getMinimumCGPA()) {
            return false;
        }

        Set<String> studentSkills = getStudentSkillNames(student);

        if (!studentSkills.containsAll(jobRole.getRequiredSkills())) {
            return false;
        }

        if (assessment != null && assessment.getAverageScore() < 60) {
            return false;
        }

        return true;
    }

    public Set<String> getMissingSkills(Student student, JobRole jobRole) {
        Set<String> studentSkills = getStudentSkillNames(student);
        Set<String> missingSkills = new HashSet<>(jobRole.getRequiredSkills());

        missingSkills.removeAll(studentSkills);

        return missingSkills;
    }

    public String getEligibilityReason(Student student, JobRole jobRole,
                                       Assessment assessment) {
        if (student.getCgpa() < jobRole.getMinimumCGPA()) {
            return "CGPA is below the required minimum.";
        }

        Set<String> missingSkills = getMissingSkills(student, jobRole);

        if (!missingSkills.isEmpty()) {
            return "Missing required skills: " + missingSkills;
        }

        if (assessment != null && assessment.getAverageScore() < 60) {
            return "Assessment average is below 60.";
        }

        return "Student is eligible.";
    }

    private Set<String> getStudentSkillNames(Student student) {
        Set<String> skillNames = new HashSet<>();

        for (Skill skill : student.getSkills()) {
            skillNames.add(skill.getSkillName());
        }

        return skillNames;
    }
}