package service;

import exception.InvalidStudentException;
import model.Student;
import util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student)
            throws InvalidStudentException {

        if (student == null) {
            throw new InvalidStudentException(
                    "Student cannot be null."
            );
        }

        if (!InputValidator.isValidId(student.getUserId())) {
            throw new InvalidStudentException(
                    "Student ID cannot be empty."
            );
        }

        if (!InputValidator.isValidName(student.getName())) {
            throw new InvalidStudentException(
                    "Student name cannot be empty."
            );
        }

        if (!InputValidator.isValidEmail(student.getEmail())) {
            throw new InvalidStudentException(
                    "Invalid email address."
            );
        }

        if (!InputValidator.isValidCgpa(student.getCgpa())) {
            throw new InvalidStudentException(
                    "CGPA must be between 0 and 10."
            );
        }

        if (!InputValidator.isValidGraduationYear(
                student.getGraduationYear())) {

            throw new InvalidStudentException(
                    "Invalid graduation year."
            );
        }

        if (findStudentById(student.getUserId()) != null) {
            throw new InvalidStudentException(
                    "Student ID already exists."
            );
        }

        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student findStudentById(String studentId) {

        for (Student student : students) {

            if (student.getUserId()
                    .equalsIgnoreCase(studentId)) {

                return student;
            }
        }

        return null;
    }

    public boolean updateStudent(
            String studentId,
            String name,
            String email,
            String branch,
            double cgpa,
            int graduationYear)
            throws InvalidStudentException {

        Student student = findStudentById(studentId);

        if (student == null) {
            throw new InvalidStudentException(
                    "Student not found."
            );
        }

        if (!InputValidator.isValidName(name)) {
            throw new InvalidStudentException(
                    "Student name cannot be empty."
            );
        }

        if (!InputValidator.isValidEmail(email)) {
            throw new InvalidStudentException(
                    "Invalid email address."
            );
        }

        if (!InputValidator.isValidCgpa(cgpa)) {
            throw new InvalidStudentException(
                    "CGPA must be between 0 and 10."
            );
        }

        if (!InputValidator.isValidGraduationYear(
                graduationYear)) {

            throw new InvalidStudentException(
                    "Invalid graduation year."
            );
        }

        student.setName(name);
        student.setEmail(email);
        student.setBranch(branch);
        student.setCgpa(cgpa);
        student.setGraduationYear(graduationYear);

        return true;
    }

    public boolean removeStudent(String studentId)
            throws InvalidStudentException {

        Student student = findStudentById(studentId);

        if (student == null) {
            throw new InvalidStudentException(
                    "Student not found."
            );
        }

        students.remove(student);

        return true;
    }
    public void loadStudents(List<Student> loadedStudents) {
    students.clear();
    students.addAll(loadedStudents);
}

    public int getStudentCount() {
        return students.size();
    }
}
