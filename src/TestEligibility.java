import model.Assessment;
import model.JobRole;
import model.Skill;
import model.Student;
import service.EligibilityEngine;

public class TestEligibility {
    public static void main(String[] args) {

        Student student = new Student(
                "S101",
                "Vidushi",
                "vidushi@example.com",
                "CSE",
                8.7,
                2027
        );

        student.addSkill(
                new Skill("Java", Skill.ProficiencyLevel.ADVANCED)
        );

        student.addSkill(
                new Skill("DSA", Skill.ProficiencyLevel.INTERMEDIATE)
        );

        student.addSkill(
                new Skill("SQL", Skill.ProficiencyLevel.INTERMEDIATE)
        );

        JobRole jobRole = new JobRole(
                "J101",
                "Software Developer",
                7.5
        );

        jobRole.addRequiredSkill("Java");
        jobRole.addRequiredSkill("DSA");
        jobRole.addRequiredSkill("SQL");
        jobRole.addRequiredSkill("Spring");
        jobRole.addPreferredSkill("Git");

        Assessment assessment = new Assessment(
                "A101",
                "S101",
                80,
                85,
                75,
                90
        );

        EligibilityEngine engine = new EligibilityEngine();

        boolean eligible = engine.isEligible(
                student,
                jobRole,
                assessment
        );

        System.out.println("Eligible: " + eligible);
        System.out.println(
                "Reason: " +
                engine.getEligibilityReason(student, jobRole, assessment)
        );

        System.out.println(
                "Missing Skills: " +
                engine.getMissingSkills(student, jobRole)
        );
    }
}