import util.InputValidator;

public class TestInputValidator {

    public static void main(String[] args) {

        System.out.println("Valid Name: "
                + InputValidator.isValidName("Vidushi"));

        System.out.println("Empty Name: "
                + InputValidator.isValidName(""));

        System.out.println("Valid Email: "
                + InputValidator.isValidEmail("vidushi@example.com"));

        System.out.println("Invalid Email: "
                + InputValidator.isValidEmail("vidushi"));

        System.out.println("Valid CGPA: "
                + InputValidator.isValidCgpa(8.7));

        System.out.println("Invalid CGPA: "
                + InputValidator.isValidCgpa(12));

        System.out.println("Valid Score: "
                + InputValidator.isValidScore(85));

        System.out.println("Invalid Score: "
                + InputValidator.isValidScore(105));

        System.out.println("Valid ID: "
                + InputValidator.isValidId("S101"));
    }
}