import exception.InvalidStudentException;
import exception.InvalidCompanyException;
import exception.EligibilityException;

public class TestExceptions {

    public static void main(String[] args) {

        try {
            throw new InvalidStudentException(
                    "Student details are invalid."
            );
        } catch (InvalidStudentException e) {
            System.out.println("Student Error: " + e.getMessage());
        }

        try {
            throw new InvalidCompanyException(
                    "Company details are invalid."
            );
        } catch (InvalidCompanyException e) {
            System.out.println("Company Error: " + e.getMessage());
        }

        try {
            throw new EligibilityException(
                    "Student is not eligible for this job."
            );
        } catch (EligibilityException e) {
            System.out.println("Eligibility Error: " + e.getMessage());
        }
    }
}