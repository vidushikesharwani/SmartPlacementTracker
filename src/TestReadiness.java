import model.Assessment;
import model.Certification;
import model.Project;
import model.Skill;
import model.Student;
import model.ReadinessReport;
import service.ReadinessAnalyzer;

public class TestReadiness {

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

        Assessment assessment = new Assessment(
                "A101",
                "S101",
                80,
                85,
                75,
                90
        );

        ReadinessAnalyzer analyzer = new ReadinessAnalyzer();

        ReadinessReport report =
                analyzer.analyze(student, assessment);

        System.out.println("===== PLACEMENT READINESS REPORT =====");
        System.out.println("Student ID: " + report.getStudentId());
        System.out.println("CGPA Score: " + report.getCgpaScore());
        System.out.println("Skill Score: " + report.getSkillScore());
        System.out.println("Project Score: " + report.getProjectScore());
        System.out.println("Certification Score: "
                + report.getCertificationScore());
        System.out.println("Assessment Score: "
                + report.getAssessmentScore());
        System.out.println("Overall Readiness Score: "
                + report.getOverallScore());

        System.out.println("\nStrengths:");
        for (String strength : report.getStrengths()) {
            System.out.println("- " + strength);
        }

        System.out.println("\nImprovement Areas:");
        for (String area : report.getImprovementAreas()) {
            System.out.println("- " + area);
        }
    }
}