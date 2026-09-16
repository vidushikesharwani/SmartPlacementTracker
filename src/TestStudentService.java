import exception.InvalidStudentException;
import model.Student;
import service.StudentService;

public class TestStudentService {

    public static void main(String[] args) {

        StudentService service = new StudentService();

        try {

            Student student1 = new Student(
                    "S101",
                    "Vidushi",
                    "vidushi@example.com",
                    "CSE",
                    8.7,
                    2027
            );

            Student student2 = new Student(
                    "S102",
                    "Aditi",
                    "aditi@example.com",
                    "CSE",
                    8.2,
                    2027
            );

            service.addStudent(student1);
            service.addStudent(student2);

            System.out.println(
                    "Total Students: "
                    + service.getStudentCount()
            );

            Student found =
                    service.findStudentById("S101");

            if (found != null) {
                System.out.println("\nStudent Found:");
                found.displayStudent();
            }

            service.updateStudent(
                    "S101",
                    "Vidushi Kesharwani",
                    "vidushi@example.com",
                    "CSE",
                    9.0,
                    2027
            );

            System.out.println("\nUpdated Student:");
            service.findStudentById("S101").displayStudent();

            service.removeStudent("S102");

            System.out.println(
                    "\nStudents Remaining: "
                    + service.getStudentCount()
            );

        } catch (InvalidStudentException e) {

            System.out.println(
                    "Student Error: " + e.getMessage()
            );
        }
    }
}