import model.Assessment;
import model.Certification;
import model.JobRole;
import model.Project;
import model.Skill;
import model.Student;
import service.MatchingEngine;

public class TestMatching {
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

        student.addSkill(
                new Skill("Git", Skill.ProficiencyLevel.INTERMEDIATE)
        );

        Project project = new Project(
                "P101",
                "Smart Placement Tracker",
                "Java based placement readiness system"
        );

        project.addTechnology("Java");
        project.addTechnology("Collections");

        student.addProject(project);

        Certification certification = new Certification(
                "C101",
                "Java Programming",
                "Oracle",
                2026
        );

        student.addCertification(certification);

        JobRole jobRole = new JobRole(
                "J101",
                "Software Developer",
                7.5
        );

        jobRole.addRequiredSkill("Java");
        jobRole.addRequiredSkill("DSA");
        jobRole.addRequiredSkill("SQL");

        jobRole.addPreferredSkill("Git");
        jobRole.addPreferredSkill("Spring");

        Assessment assessment = new Assessment(
                "A101",
                "S101",
                80,
                85,
                75,
                90
        );

        MatchingEngine engine = new MatchingEngine();

        var result = engine.calculateMatch(
                student,
                jobRole,
                assessment,
                "C101"
        );

        System.out.println("===== PLACEMENT MATCH RESULT =====");
        System.out.println("Student ID: " + result.getStudentId());
        System.out.println("Company ID: " + result.getCompanyId());
        System.out.println("Job ID: " + result.getJobId());
        System.out.println("Eligible: " + result.isEligible());
        System.out.println("CGPA Score: " + result.getCgpaScore());
        System.out.println("Required Skill Score: "
                + result.getRequiredSkillScore());
        System.out.println("Preferred Skill Score: "
                + result.getPreferredSkillScore());
        System.out.println("Profile Score: "
                + result.getProfileScore());
        System.out.println("Overall Match Score: "
                + result.getOverallMatchScore());
        System.out.println("Match Category: "
                + engine.getMatchCategory(result.getOverallMatchScore()));
        System.out.println("Missing Skills: "
                + result.getMissingSkills());
    }
}