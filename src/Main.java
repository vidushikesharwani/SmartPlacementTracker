import exception.InvalidCompanyException;
import exception.InvalidStudentException;
import io.FileManager;
import model.Student;
import service.StudentService;
import util.ReportGenerator;
import model.Project;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import model.Assessment;
import model.ReadinessReport;
import service.ReadinessAnalyzer;
import model.Company;
import model.JobRole;
import model.PlacementResult;
import service.CompanyService;
import service.EligibilityEngine;
import service.MatchingEngine;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static StudentService studentService = new StudentService();
    private static FileManager fileManager = new FileManager();
    private static CompanyService companyService =
        new CompanyService();
    public static void main(String[] args) {

        loadStudentData();

        boolean running = true;

        while (running) {

            System.out.println("\n===== SMART PLACEMENT & SKILL TRACKER =====");
            System.out.println("1. Student");
            System.out.println("2. Placement Admin");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    studentMenu();
                    break;

                case "2":
                    adminMenu();
                    break;

                case "3":
                    running = false;
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    private static void loadStudentData() {

    try {
        studentService.loadStudents(
                fileManager.loadStudents()
        );

        System.out.println(
                studentService.getStudentCount()
                + " student(s) loaded."
        );

        companyService.loadCompanies(
                fileManager.loadCompanies()
        );

        System.out.println(
                companyService.getCompanyCount()
                + " company(s) loaded."
        );

    } catch (IOException e) {

        System.out.println(
                "Could not load data."
        );
    }
}

    private static void studentMenu() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== STUDENT PORTAL =====");
            System.out.println("1. Create Student Profile");
            System.out.println("2. View Profile");
            System.out.println("3. Update Profile");
            System.out.println("4. Manage Skills");
            System.out.println("5. Manage Projects");
            System.out.println("6. Manage Certifications");
            System.out.println("7. Check Placement Readiness");
            System.out.println("8. Find Matching Companies");
            System.out.println("9. View Skill Gaps");
            System.out.println("10. Logout");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    createStudentProfile();
                    break;

                case "2":
                    viewStudentProfile();
                    break;

                case "3":
                     updateStudentProfile();
                     break;    
                case "4":
                    manageSkills();
                    break;
                case "5":
    manageProjects();
    break;
                case "6":
    manageCertifications();
    break;
    case "7":
    checkPlacementReadiness();
    break;
    case "8":
    findMatchingCompanies();
    break;

    case "9":
    viewSkillGaps();
    break;
                case "10":
                    running = false;
                    System.out.println("Logged out.");
                    break;

                default:
                    System.out.println(
                            "This feature will be connected next."
                    );
            }
        }
    }

    

    private static void updateStudentProfile() {

    System.out.println("\n===== UPDATE STUDENT PROFILE =====");

    System.out.print("Enter Student ID: ");
    String studentId = scanner.nextLine();

    Student student =
            studentService.findStudentById(studentId);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    System.out.println("Enter new details:");

    System.out.print("Name: ");
    String name = scanner.nextLine();

    System.out.print("Email: ");
    String email = scanner.nextLine();

    System.out.print("Branch: ");
    String branch = scanner.nextLine();

    System.out.print("CGPA: ");
    double cgpa;

    try {
        cgpa = Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid CGPA.");
        return;
    }

    System.out.print("Graduation Year: ");
    int graduationYear;

    try {
        graduationYear = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid graduation year.");
        return;
    }

    try {

        studentService.updateStudent(
                studentId,
                name,
                email,
                branch,
                cgpa,
                graduationYear
        );

        fileManager.saveStudents(
                studentService.getStudents()
        );

        System.out.println(
                "Student profile updated successfully."
        );

    } catch (InvalidStudentException e) {

        System.out.println(
                "Error: " + e.getMessage()
        );

    } catch (IOException e) {

        System.out.println(
                "Updated profile could not be saved."
        );
    }
}
private static void viewSkillGaps() {

    System.out.println("\n===== VIEW SKILL GAPS =====");

    System.out.print("Enter Student ID: ");
    String studentId = scanner.nextLine();

    Student student =
            studentService.findStudentById(studentId);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    if (companyService.getCompanies().isEmpty()) {
        System.out.println("No companies available.");
        return;
    }

    System.out.println("\nAvailable Companies:");

    for (Company company : companyService.getCompanies()) {

        System.out.println(
                company.getCompanyId()
                + " - "
                + company.getCompanyName()
        );
    }

    System.out.print("\nEnter Company ID: ");
    String companyId = scanner.nextLine();

    Company company =
            companyService.findCompanyById(companyId);

    if (company == null) {
        System.out.println("Company not found.");
        return;
    }

    if (company.getJobRoles().isEmpty()) {
        System.out.println(
                "No job roles available for this company."
        );
        return;
    }

    System.out.println("\nAvailable Job Roles:");

    for (JobRole jobRole : company.getJobRoles()) {

        System.out.println(
                jobRole.getJobId()
                + " - "
                + jobRole.getRoleName()
        );
    }

    System.out.print("\nEnter Job ID: ");
    String jobId = scanner.nextLine();

    JobRole selectedJob = null;

    for (JobRole jobRole : company.getJobRoles()) {

        if (jobRole.getJobId()
                .equalsIgnoreCase(jobId)) {

            selectedJob = jobRole;
            break;
        }
    }

    if (selectedJob == null) {
        System.out.println("Job role not found.");
        return;
    }

    EligibilityEngine eligibilityEngine =
            new EligibilityEngine();

    Set<String> missingSkills =
            eligibilityEngine.getMissingSkills(
                    student,
                    selectedJob
            );

    System.out.println("\n===== SKILL GAP REPORT =====");

    System.out.println(
            "Student: " + student.getName()
    );

    System.out.println(
            "Company: " + company.getCompanyName()
    );

    System.out.println(
            "Job Role: " + selectedJob.getRoleName()
    );

    System.out.println(
            "\nRequired Skills: "
            + selectedJob.getRequiredSkills()
    );

    System.out.println(
            "Your Skills: "
            + student.getSkills()
    );

    if (missingSkills.isEmpty()) {

        System.out.println(
                "\nMissing Required Skills: None"
        );

        System.out.println(
                "You have all required skills for this role."
        );

    } else {

        System.out.println(
                "\nMissing Required Skills: "
                + missingSkills
        );

        System.out.println(
                "Focus on these skills to improve your eligibility."
        );
    }
}
private static void findMatchingCompanies() {

    System.out.println("\n===== FIND MATCHING COMPANIES =====");

    System.out.print("Enter Student ID: ");
    String studentId = scanner.nextLine();

    Student student =
            studentService.findStudentById(studentId);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    if (companyService.getCompanies().isEmpty()) {
        System.out.println("No companies available.");
        return;
    }

    System.out.println("\nAvailable Companies:");

    for (Company company : companyService.getCompanies()) {
        System.out.println(
                company.getCompanyId() + " - "
                + company.getCompanyName()
        );
    }

    System.out.print("\nEnter Company ID: ");
    String companyId = scanner.nextLine();

    Company company =
            companyService.findCompanyById(companyId);

    if (company == null) {
        System.out.println("Company not found.");
        return;
    }

    if (company.getJobRoles().isEmpty()) {
        System.out.println("No job roles available for this company.");
        return;
    }

    System.out.println("\nAvailable Job Roles:");

    for (JobRole jobRole : company.getJobRoles()) {
        System.out.println(
                jobRole.getJobId() + " - "
                + jobRole.getRoleName()
        );
    }

    System.out.print("\nEnter Job ID: ");
    String jobId = scanner.nextLine();

    JobRole selectedJob = null;

    for (JobRole jobRole : company.getJobRoles()) {
        if (jobRole.getJobId().equalsIgnoreCase(jobId)) {
            selectedJob = jobRole;
            break;
        }
    }

    if (selectedJob == null) {
        System.out.println("Job role not found.");
        return;
    }

    System.out.println("\nEnter Assessment Scores");

    System.out.print("DSA Score: ");
    double dsaScore;

    try {
        dsaScore = Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid score.");
        return;
    }

    System.out.print("Java Score: ");
    double javaScore;

    try {
        javaScore = Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid score.");
        return;
    }

    System.out.print("Aptitude Score: ");
    double aptitudeScore;

    try {
        aptitudeScore = Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid score.");
        return;
    }

    System.out.print("Communication Score: ");
    double communicationScore;

    try {
        communicationScore =
                Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid score.");
        return;
    }

    if (!util.InputValidator.isValidScore(dsaScore) ||
        !util.InputValidator.isValidScore(javaScore) ||
        !util.InputValidator.isValidScore(aptitudeScore) ||
        !util.InputValidator.isValidScore(communicationScore)) {

        System.out.println(
                "Scores must be between 0 and 100."
        );
        return;
    }

    Assessment assessment =
            new Assessment(
                    "A-" + studentId,
                    studentId,
                    dsaScore,
                    javaScore,
                    aptitudeScore,
                    communicationScore
            );

    MatchingEngine matchingEngine =
            new MatchingEngine();

    PlacementResult result =
            matchingEngine.calculateMatch(
                    student,
                    selectedJob,
                    assessment,
                    company.getCompanyId()
            );

    System.out.println("\n===== MATCHING RESULT =====");

    System.out.println(
            "Company: " + company.getCompanyName()
    );

    System.out.println(
            "Job Role: " + selectedJob.getRoleName()
    );

    System.out.println(
            "Eligible: " + result.isEligible()
    );

    if (!result.isEligible()) {

        System.out.println(
                "Reason: "
                + new service.EligibilityEngine()
                    .getEligibilityReason(
                            student,
                            selectedJob,
                            assessment
                    )
        );

        if (!result.getMissingSkills().isEmpty()) {
            System.out.println(
                    "Missing Required Skills: "
                    + result.getMissingSkills()
            );
        }

        return;
    }

    System.out.printf(
            "CGPA Score: %.2f%n",
            result.getCgpaScore()
    );

    System.out.printf(
            "Required Skill Score: %.2f%n",
            result.getRequiredSkillScore()
    );

    System.out.printf(
            "Preferred Skill Score: %.2f%n",
            result.getPreferredSkillScore()
    );

    System.out.printf(
            "Profile Score: %.2f%n",
            result.getProfileScore()
    );

    System.out.printf(
            "Overall Match Score: %.2f%n",
            result.getOverallMatchScore()
    );

    MatchingEngine engine =
            new MatchingEngine();

    System.out.println(
            "Match Category: "
            + engine.getMatchCategory(
                    result.getOverallMatchScore()
            )
    );

    if (!result.getMissingSkills().isEmpty()) {
        System.out.println(
                "Missing Skills: "
                + result.getMissingSkills()
        );
    } else {
        System.out.println(
                "Missing Skills: None"
        );
    }
}
private static void checkPlacementReadiness() {

    System.out.println("\n===== PLACEMENT READINESS =====");

    System.out.print("Enter Student ID: ");
    String studentId = scanner.nextLine();

    Student student =
            studentService.findStudentById(studentId);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    System.out.println("\nEnter Assessment Scores");

    System.out.print("DSA Score: ");
    double dsaScore;

    try {
        dsaScore = Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid score.");
        return;
    }

    System.out.print("Java Score: ");
    double javaScore;

    try {
        javaScore = Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid score.");
        return;
    }

    System.out.print("Aptitude Score: ");
    double aptitudeScore;

    try {
        aptitudeScore = Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid score.");
        return;
    }

    System.out.print("Communication Score: ");
    double communicationScore;

    try {
        communicationScore =
                Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid score.");
        return;
    }

    if (!util.InputValidator.isValidScore(dsaScore) ||
        !util.InputValidator.isValidScore(javaScore) ||
        !util.InputValidator.isValidScore(aptitudeScore) ||
        !util.InputValidator.isValidScore(communicationScore)) {

        System.out.println(
                "Scores must be between 0 and 100."
        );
        return;
    }

    Assessment assessment =
            new Assessment(
                    "A-" + studentId,
                    studentId,
                    dsaScore,
                    javaScore,
                    aptitudeScore,
                    communicationScore
            );

    ReadinessAnalyzer analyzer =
            new ReadinessAnalyzer();

    ReadinessReport report =
            analyzer.analyze(student, assessment);

    System.out.println("\n===== READINESS REPORT =====");

    System.out.println(
            "Student ID: " + report.getStudentId()
    );

    System.out.printf(
            "CGPA Score: %.2f%n",
            report.getCgpaScore()
    );

    System.out.printf(
            "Technical Skill Score: %.2f%n",
            report.getSkillScore()
    );

    System.out.printf(
            "Project Score: %.2f%n",
            report.getProjectScore()
    );

    System.out.printf(
            "Certification Score: %.2f%n",
            report.getCertificationScore()
    );

    System.out.printf(
            "Assessment Score: %.2f%n",
            report.getAssessmentScore()
    );

    System.out.printf(
            "Overall Readiness Score: %.2f%n",
            report.getOverallScore()
    );

    System.out.println("\nStrengths:");

    if (report.getStrengths().isEmpty()) {
        System.out.println("None");
    } else {
        for (String strength : report.getStrengths()) {
            System.out.println("- " + strength);
        }
    }

    System.out.println("\nImprovement Areas:");

    if (report.getImprovementAreas().isEmpty()) {
        System.out.println("None");
    } else {
        for (String area : report.getImprovementAreas()) {
            System.out.println("- " + area);
        }
    }
}
private static void manageCertifications() {

    System.out.println("\n===== MANAGE CERTIFICATIONS =====");

    System.out.print("Enter Student ID: ");
    String studentId = scanner.nextLine();

    Student student =
            studentService.findStudentById(studentId);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    boolean running = true;

    while (running) {

        System.out.println("\n1. View Certifications");
        System.out.println("2. Add Certification");
        System.out.println("3. Back");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {

            case "1":
                viewCertifications(student);
                break;

            case "2":
                addCertification(student);
                break;

            case "3":
                running = false;
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}
private static void viewCertifications(Student student) {

    System.out.println("\n===== MY CERTIFICATIONS =====");

    if (student.getCertifications().isEmpty()) {
        System.out.println("No certifications added.");
        return;
    }

    int number = 1;

    for (model.Certification certification
            : student.getCertifications()) {

        System.out.println("\nCertification " + number);
        System.out.println(
                "Certification ID: "
                + certification.getCertificationId()
        );
        System.out.println(
                "Name: "
                + certification.getName()
        );
        System.out.println(
                "Issuing Organization: "
                + certification.getIssuingOrganization()
        );
        System.out.println(
                "Year: "
                + certification.getYear()
        );

        number++;
    }
}

private static void addCertification(Student student) {

    System.out.println("\n===== ADD CERTIFICATION =====");

    System.out.print("Enter Certification ID: ");
    String certificationId = scanner.nextLine();

    if (!util.InputValidator.isValidId(certificationId)) {
        System.out.println("Certification ID cannot be empty.");
        return;
    }

    System.out.print("Enter Certification Name: ");
    String name = scanner.nextLine();

    if (!util.InputValidator.isValidName(name)) {
        System.out.println("Certification name cannot be empty.");
        return;
    }

    System.out.print("Enter Issuing Organization: ");
    String organization = scanner.nextLine();

    if (!util.InputValidator.isValidName(organization)) {
        System.out.println(
                "Issuing organization cannot be empty."
        );
        return;
    }

    System.out.print("Enter Year: ");
    int year;

    try {
        year = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid year.");
        return;
    }

    if (year < 2000 || year > 2035) {
        System.out.println("Invalid certification year.");
        return;
    }

    for (model.Certification certification
            : student.getCertifications()) {

        if (certification.getCertificationId()
                .equalsIgnoreCase(certificationId)) {

            System.out.println(
                    "Certification ID already exists."
            );
            return;
        }
    }

    model.Certification certification =
            new model.Certification(
                    certificationId,
                    name.trim(),
                    organization.trim(),
                    year
            );

    student.addCertification(certification);

    try {

        fileManager.saveStudents(
                studentService.getStudents()
        );

        System.out.println(
                "Certification added successfully."
        );

    } catch (IOException e) {

        System.out.println(
                "Certification added, but data could not be saved."
        );
    }
}
private static void manageProjects() {

    System.out.println("\n===== MANAGE PROJECTS =====");

    System.out.print("Enter Student ID: ");
    String studentId = scanner.nextLine();

    Student student =
            studentService.findStudentById(studentId);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    boolean running = true;

    while (running) {

        System.out.println("\n1. View Projects");
        System.out.println("2. Add Project");
        System.out.println("3. Back");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {

            case "1":
                viewProjects(student);
                break;

            case "2":
                addProject(student);
                break;

            case "3":
                running = false;
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}
private static void viewProjects(Student student) {

    System.out.println("\n===== MY PROJECTS =====");

    if (student.getProjects().isEmpty()) {
        System.out.println("No projects added.");
        return;
    }

    int number = 1;

    for (Project project : student.getProjects()) {

        System.out.println("\nProject " + number);
        System.out.println("Project ID: " + project.getProjectId());
        System.out.println("Project Name: " + project.getProjectName());
        System.out.println("Description: " + project.getDescription());
        System.out.println("Technologies: " + project.getTechnologies());

        number++;
    }
}
private static void addProject(Student student) {

    System.out.println("\n===== ADD PROJECT =====");

    System.out.print("Enter Project ID: ");
    String projectId = scanner.nextLine();

    if (!util.InputValidator.isValidId(projectId)) {
        System.out.println("Project ID cannot be empty.");
        return;
    }

    System.out.print("Enter Project Name: ");
    String projectName = scanner.nextLine();

    if (!util.InputValidator.isValidName(projectName)) {
        System.out.println("Project name cannot be empty.");
        return;
    }

    System.out.print("Enter Project Description: ");
    String description = scanner.nextLine();

    if (!util.InputValidator.isValidName(description)) {
        System.out.println("Project description cannot be empty.");
        return;
    }

    for (Project project : student.getProjects()) {

        if (project.getProjectId()
                .equalsIgnoreCase(projectId)) {

            System.out.println("Project ID already exists.");
            return;
        }
    }

    Project project =
            new Project(projectId, projectName, description);

    System.out.print("Enter number of technologies: ");
    int count;

    try {
        count = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid number.");
        return;
    }

    if (count <= 0) {
        System.out.println("At least one technology is required.");
        return;
    }

    for (int i = 1; i <= count; i++) {

        System.out.print("Enter technology " + i + ": ");
        String technology = scanner.nextLine();

        if (!util.InputValidator.isValidSkill(technology)) {
            System.out.println("Technology cannot be empty.");
            return;
        }

        project.addTechnology(technology.trim());
    }

    student.addProject(project);

    try {

        fileManager.saveStudents(
                studentService.getStudents()
        );

        System.out.println(
                "Project added successfully."
        );

    } catch (IOException e) {

        System.out.println(
                "Project added, but data could not be saved."
        );
    }
}
    private static void viewStudentProfile() {

    System.out.println("\n===== VIEW STUDENT PROFILE =====");

    System.out.print("Enter Student ID: ");
    String studentId = scanner.nextLine();

    Student student =
            studentService.findStudentById(studentId);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    student.displayStudent();
}
 
    private static void manageSkills() {

    System.out.println("\n===== MANAGE SKILLS =====");

    System.out.print("Enter Student ID: ");
    String studentId = scanner.nextLine();

    Student student =
            studentService.findStudentById(studentId);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    boolean running = true;

    while (running) {

        System.out.println("\n1. View Skills");
        System.out.println("2. Add Skill");
        System.out.println("3. Update Skill Proficiency");
        System.out.println("4. Back");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {

            case "1":
                viewSkills(student);
                break;

            case "2":
                addSkill(student);
                break;

            case "3":
                updateSkillProficiency(student);
                break;

            case "4":
                running = false;
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}
private static void viewSkills(Student student) {

    System.out.println("\n===== MY SKILLS =====");

    if (student.getSkills().isEmpty()) {
        System.out.println("No skills added.");
        return;
    }

    int number = 1;

    for (model.Skill skill : student.getSkills()) {

        System.out.println(
                number + ". " +
                skill.getSkillName() +
                " - " +
                skill.getProficiencyLevel()
        );

        number++;
    }
}
private static void addSkill(Student student) {

    System.out.print("\nEnter skill name: ");
    String skillName = scanner.nextLine();

    if (!util.InputValidator.isValidSkill(skillName)) {
        System.out.println("Skill name cannot be empty.");
        return;
    }

    System.out.println("\nSelect proficiency:");
    System.out.println("1. BEGINNER");
    System.out.println("2. INTERMEDIATE");
    System.out.println("3. ADVANCED");
    System.out.println("4. EXPERT");

    System.out.print("Enter choice: ");
    String choice = scanner.nextLine();

    model.Skill.ProficiencyLevel level;

    switch (choice) {

        case "1":
            level = model.Skill.ProficiencyLevel.BEGINNER;
            break;

        case "2":
            level = model.Skill.ProficiencyLevel.INTERMEDIATE;
            break;

        case "3":
            level = model.Skill.ProficiencyLevel.ADVANCED;
            break;

        case "4":
            level = model.Skill.ProficiencyLevel.EXPERT;
            break;

        default:
            System.out.println("Invalid proficiency choice.");
            return;
    }

    model.Skill skill =
            new model.Skill(skillName.trim(), level);

    if (student.getSkills().contains(skill)) {
        System.out.println("Skill already exists.");
        return;
    }

    student.addSkill(skill);

    try {

        fileManager.saveStudents(
                studentService.getStudents()
        );

        System.out.println("Skill added successfully.");

    } catch (IOException e) {

        System.out.println(
                "Skill added, but data could not be saved."
        );
    }
}

private static void updateSkillProficiency(Student student) {

    if (student.getSkills().isEmpty()) {
        System.out.println("No skills available.");
        return;
    }

    viewSkills(student);

    System.out.print("\nEnter skill name to update: ");
    String skillName = scanner.nextLine();

    model.Skill selectedSkill = null;

    for (model.Skill skill : student.getSkills()) {

        if (skill.getSkillName()
                .equalsIgnoreCase(skillName)) {

            selectedSkill = skill;
            break;
        }
    }

    if (selectedSkill == null) {
        System.out.println("Skill not found.");
        return;
    }

    System.out.println("\nSelect new proficiency:");
    System.out.println("1. BEGINNER");
    System.out.println("2. INTERMEDIATE");
    System.out.println("3. ADVANCED");
    System.out.println("4. EXPERT");

    System.out.print("Enter choice: ");
    String choice = scanner.nextLine();

    model.Skill.ProficiencyLevel level;

    switch (choice) {

        case "1":
            level = model.Skill.ProficiencyLevel.BEGINNER;
            break;

        case "2":
            level = model.Skill.ProficiencyLevel.INTERMEDIATE;
            break;

        case "3":
            level = model.Skill.ProficiencyLevel.ADVANCED;
            break;

        case "4":
            level = model.Skill.ProficiencyLevel.EXPERT;
            break;

        default:
            System.out.println("Invalid proficiency choice.");
            return;
    }

    selectedSkill.setProficiencyLevel(level);

    try {

        fileManager.saveStudents(
                studentService.getStudents()
        );

        System.out.println(
                "Skill proficiency updated successfully."
        );

    } catch (IOException e) {

        System.out.println(
                "Skill updated, but data could not be saved."
        );
    }
}

    private static void createStudentProfile() {

        System.out.println("\n===== CREATE STUDENT PROFILE =====");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Branch: ");
        String branch = scanner.nextLine();

        System.out.print("Enter CGPA: ");
        double cgpa;

        try {
            cgpa = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid CGPA.");
            return;
        }

        System.out.print("Enter Graduation Year: ");
        int graduationYear;

        try {
            graduationYear = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid graduation year.");
            return;
        }

        Student student = new Student(
                studentId,
                name,
                email,
                branch,
                cgpa,
                graduationYear
        );

        try {

            studentService.addStudent(student);

            fileManager.saveStudents(
                    studentService.getStudents()
            );

            System.out.println(
                    "Student profile created successfully."
            );

        } catch (InvalidStudentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        } catch (IOException e) {

            System.out.println(
                    "Profile created, but data could not be saved."
            );
        }
    }
    private static void manageCompanies() {

    System.out.println("\n===== MANAGE COMPANIES =====");

    boolean running = true;

    while (running) {

        System.out.println("\n1. View Companies");
        System.out.println("2. Add Company");
        System.out.println("3. Update Company");
        System.out.println("4. Remove Company");
        System.out.println("5. Back");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {

            case "1":
                viewCompanies();
                break;

            case "2":
                addCompany();
                break;

            case "3":
                updateCompany();
                break;

            case "4":
                removeCompany();
                break;

            case "5":
                running = false;
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}
private static void viewCompanies() {

    System.out.println("\n===== COMPANIES =====");

    if (companyService.getCompanies().isEmpty()) {
        System.out.println("No companies available.");
        return;
    }

    for (Company company : companyService.getCompanies()) {

        System.out.println("\nCompany ID: "
                + company.getCompanyId());

        System.out.println("Company Name: "
                + company.getCompanyName());

        System.out.println("Industry: "
                + company.getIndustry());

        System.out.println("Job Roles: "
                + company.getJobRoles().size());
    }
}
private static void addCompany() {

    System.out.println("\n===== ADD COMPANY =====");

    System.out.print("Enter Company ID: ");
    String companyId = scanner.nextLine();

    System.out.print("Enter Company Name: ");
    String companyName = scanner.nextLine();

    System.out.print("Enter Industry: ");
    String industry = scanner.nextLine();

    Company company =
            new Company(
                    companyId,
                    companyName,
                    industry
            );

    try {
    companyService.addCompany(company);
    fileManager.saveCompanies(companyService.getCompanies());
    System.out.println("Company added successfully.");
} catch (InvalidCompanyException e) {
    System.out.println("Error: " + e.getMessage());
} catch (IOException e) {
    System.out.println("Company could not be saved.");
}
}
private static void updateCompany() {

    System.out.println("\n===== UPDATE COMPANY =====");

    System.out.print("Enter Company ID: ");
    String companyId = scanner.nextLine();

    Company company =
            companyService.findCompanyById(companyId);

    if (company == null) {
        System.out.println("Company not found.");
        return;
    }

    System.out.print("Enter new Company Name: ");
    String companyName = scanner.nextLine();

    System.out.print("Enter new Industry: ");
    String industry = scanner.nextLine();

    try {
    companyService.updateCompany(companyId, companyName, industry);
    fileManager.saveCompanies(companyService.getCompanies());
    System.out.println("Company updated successfully.");
} catch (InvalidCompanyException e) {
    System.out.println("Error: " + e.getMessage());
} catch (IOException e) {
    System.out.println("Company could not be saved.");
}
}
private static void manageJobRoles() {

    System.out.println("\n===== MANAGE JOB ROLES =====");

    boolean running = true;

    while (running) {

        System.out.println("\n1. View Job Roles");
        System.out.println("2. Add Job Role");
        System.out.println("3. Back");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {

            case "1":
                viewJobRoles();
                break;

            case "2":
                addJobRole();
                break;

            case "3":
                running = false;
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}
private static void viewJobRoles() {

    System.out.println("\n===== JOB ROLES =====");

    if (companyService.getCompanies().isEmpty()) {
        System.out.println("No companies available.");
        return;
    }

    boolean found = false;

    for (Company company : companyService.getCompanies()) {

        if (company.getJobRoles().isEmpty()) {
            continue;
        }

        System.out.println(
                "\nCompany: " + company.getCompanyName()
        );

        for (JobRole jobRole : company.getJobRoles()) {

            found = true;

            System.out.println(
                    "Job ID: " + jobRole.getJobId()
            );

            System.out.println(
                    "Role: " + jobRole.getRoleName()
            );

            System.out.println(
                    "Minimum CGPA: "
                    + jobRole.getMinimumCGPA()
            );

            System.out.println(
                    "Required Skills: "
                    + jobRole.getRequiredSkills()
            );

            System.out.println(
                    "Preferred Skills: "
                    + jobRole.getPreferredSkills()
            );

            System.out.println();
        }
    }

    if (!found) {
        System.out.println("No job roles available.");
    }
}
private static void addJobRole() {

    System.out.println("\n===== ADD JOB ROLE =====");

    if (companyService.getCompanies().isEmpty()) {
        System.out.println("No companies available.");
        return;
    }

    System.out.println("\nAvailable Companies:");

    for (Company company : companyService.getCompanies()) {

        System.out.println(
                company.getCompanyId()
                + " - "
                + company.getCompanyName()
        );
    }

    System.out.print("\nEnter Company ID: ");
    String companyId = scanner.nextLine();

    Company company =
            companyService.findCompanyById(companyId);

    if (company == null) {
        System.out.println("Company not found.");
        return;
    }

    System.out.print("Enter Job ID: ");
    String jobId = scanner.nextLine();

    System.out.print("Enter Role Name: ");
    String roleName = scanner.nextLine();

    System.out.print("Enter Minimum CGPA: ");

    double minimumCGPA;

    try {
        minimumCGPA =
                Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid CGPA.");
        return;
    }

    if (minimumCGPA < 0 || minimumCGPA > 10) {
        System.out.println(
                "Minimum CGPA must be between 0 and 10."
        );
        return;
    }

    JobRole jobRole =
            new JobRole(
                    jobId,
                    roleName,
                    minimumCGPA
            );

    System.out.print(
            "Enter required skills separated by commas: "
    );

    String requiredInput = scanner.nextLine();

    if (!requiredInput.trim().isEmpty()) {

        String[] skills =
                requiredInput.split(",");

        for (String skill : skills) {

            if (!skill.trim().isEmpty()) {
                jobRole.addRequiredSkill(
                        skill.trim()
                );
            }
        }
    }

    System.out.print(
            "Enter preferred skills separated by commas: "
    );

    String preferredInput = scanner.nextLine();

    if (!preferredInput.trim().isEmpty()) {

        String[] skills =
                preferredInput.split(",");

        for (String skill : skills) {

            if (!skill.trim().isEmpty()) {
                jobRole.addPreferredSkill(
                        skill.trim()
                );
            }
        }
    }

    try {
    companyService.addJobRole(companyId, jobRole);
    fileManager.saveCompanies(companyService.getCompanies());
    System.out.println("Job role added successfully.");
} catch (InvalidCompanyException e) {
    System.out.println("Error: " + e.getMessage());
} catch (IOException e) {
    System.out.println("Job role could not be saved.");
}
}
private static void viewPlacementStatistics() {
    System.out.println("\n===== PLACEMENT STATISTICS =====");

    int totalStudents = studentService.getStudentCount();
    int totalCompanies = companyService.getCompanyCount();
    int totalJobRoles = companyService.getJobRoleCount();

    System.out.println("Total Students: " + totalStudents);
    System.out.println("Total Companies: " + totalCompanies);
    System.out.println("Total Job Roles: " + totalJobRoles);

    if (totalStudents > 0) {
        double totalCgpa = 0.0;

        for (Student student : studentService.getStudents()) {
            totalCgpa += student.getCgpa();
        }

        double averageCgpa = totalCgpa / totalStudents;

        System.out.printf("Average Student CGPA: %.2f%n", averageCgpa);
    } else {
        System.out.println("Average Student CGPA: No data available.");
    }
}
private static void generateReports() {
    System.out.println("\n===== GENERATE REPORTS =====");

    ReportGenerator reportGenerator = new ReportGenerator();

    reportGenerator.printStudentReport(
            studentService.getStudents()
    );

    System.out.println();

    reportGenerator.printCompanyReport(
            companyService.getCompanies()
    );
}
private static void viewStudents() {
    System.out.println("\n===== ALL STUDENTS =====");

    if (studentService.getStudents().isEmpty()) {
        System.out.println("No students available.");
        return;
    }

    for (Student student : studentService.getStudents()) {
        System.out.println("\nStudent ID: " + student.getUserId());
        System.out.println("Name: " + student.getName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Branch: " + student.getBranch());
        System.out.println("CGPA: " + student.getCgpa());
        System.out.println("Graduation Year: " + student.getGraduationYear());
    }
}
private static void searchStudent() {
    System.out.println("\n===== SEARCH STUDENT =====");
    System.out.print("Enter Student ID: ");

    String studentId = scanner.nextLine();

    Student student = studentService.findStudentById(studentId);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    student.displayStudent();
}
private static void removeCompany() {

    System.out.println("\n===== REMOVE COMPANY =====");

    System.out.print("Enter Company ID: ");
    String companyId = scanner.nextLine();

    try {
    companyService.removeCompany(companyId);
    fileManager.saveCompanies(companyService.getCompanies());
    System.out.println("Company removed successfully.");
} catch (InvalidCompanyException e) {
    System.out.println("Error: " + e.getMessage());
} catch (IOException e) {
    System.out.println("Company could not be saved.");
}
}
    private static void adminMenu() {
    boolean running = true;

    while (running) {
        System.out.println("\n===== PLACEMENT ADMIN =====");
        System.out.println("1. View Students");
        System.out.println("2. Search Student");
        System.out.println("3. Manage Companies");
        System.out.println("4. Manage Job Roles");
        System.out.println("5. View Placement Statistics");
        System.out.println("6. Generate Reports");
        System.out.println("7. Logout");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                viewStudents();
                break;

            case "2":
                searchStudent();
                break;

            case "3":
                manageCompanies();
                break;

            case "4":
                manageJobRoles();
                break;

            case "5":
                viewPlacementStatistics();
                break;

            case "6":
                generateReports();
                break;

            case "7":
                running = false;
                System.out.println("Logged out.");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}
}