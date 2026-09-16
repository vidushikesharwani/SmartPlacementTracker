import io.FileManager;
import model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestFileManager {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        students.add(
                new Student(
                        "S101",
                        "Vidushi",
                        "vidushi@example.com",
                        "CSE",
                        8.7,
                        2027
                )
        );

        students.add(
                new Student(
                        "S102",
                        "Aditi",
                        "aditi@example.com",
                        "CSE",
                        8.2,
                        2027
                )
        );

        FileManager fileManager = new FileManager();

        try {

            fileManager.saveStudents(students);

            System.out.println("Students saved successfully.");

            List<Student> loadedStudents =
                    fileManager.loadStudents();

            System.out.println(
                    "Students loaded: "
                    + loadedStudents.size()
            );

            for (Student student : loadedStudents) {
                student.displayStudent();
                System.out.println();
            }

        } catch (IOException e) {

            System.out.println(
                    "File error: " + e.getMessage()
            );
        }
    }
}