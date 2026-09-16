import model.Certification;
import model.Project;
import model.Skill;
import model.Student;

public class TestStudent {
    public static void main(String[] args) {
        Student student = new Student(
                "S101",
                "Vidushi",
                "vidushi@example.com",
                "CSE",
                8.7,
                2027
        );

        Skill java = new Skill(
                "Java",
                Skill.ProficiencyLevel.ADVANCED
        );

        Skill python = new Skill(
                "Python",
                Skill.ProficiencyLevel.INTERMEDIATE
        );

        Project project = new Project(
                "P101",
                "Smart Placement Tracker",
                "A Java based placement readiness system"
        );

        project.addTechnology("Java");
        project.addTechnology("Collections");
        project.addTechnology("File I/O");

        Certification certification = new Certification(
                "C101",
                "Java Programming",
                "Oracle",
                2026
        );

        student.addSkill(java);
        student.addSkill(python);
        student.addProject(project);
        student.addCertification(certification);

        student.displayStudent();

        System.out.println("\nSkills:");
        for (Skill skill : student.getSkills()) {
            System.out.println(skill);
        }

        System.out.println("\nProjects:");
        for (Project p : student.getProjects()) {
            System.out.println(p);
        }

        System.out.println("\nCertifications:");
        for (Certification c : student.getCertifications()) {
            System.out.println(c);
        }
    }
}