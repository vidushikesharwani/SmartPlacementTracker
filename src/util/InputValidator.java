package util;

public class InputValidator {

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null
                && email.contains("@")
                && email.contains(".");
    }

    public static boolean isValidCgpa(double cgpa) {
        return cgpa >= 0.0 && cgpa <= 10.0;
    }

    public static boolean isValidGraduationYear(int year) {
        return year >= 2020 && year <= 2035;
    }

    public static boolean isValidScore(double score) {
        return score >= 0.0 && score <= 100.0;
    }

    public static boolean isValidId(String id) {
        return id != null && !id.trim().isEmpty();
    }

    public static boolean isValidSkill(String skill) {
        return skill != null && !skill.trim().isEmpty();
    }
}