# Smart Placement & Skill Tracker

## Project Overview

Smart Placement & Skill Tracker is a Javabased commandline application designed to help students manage their placement profiles, evaluate placement readiness, and identify suitable job roles based on their academic and skill profiles.

The system allows students to maintain information such as CGPA, technical skills, projects, certificaations and assessment performance. It provides eligibility checking, company and job-role matching, placement readiness analysis, and skill gap identification.

Placement administrators can manage student records, companies, and job roles and can generate placement-related statistics and reports.

The project demonstrates practical application of Java concepts including Object-Oriented Programming, inheritance, encapsulation, collections, enums, exception handling, file handling, input validatiion, and modular package based design.

---

## Features

### 1. Student Profile Management

- Create student profiles
- View student profiles
- Update personal and academic information
- Validate student details
- Prevent duplicate student IDs

### 2. Skill & Qualification Management

- Add and manage technical skills
- Assign proficiency levels to skills
- Add projects and technologies used
- Add certifications
- Maintain placement-related profile information

### 3. Placement Readiness Analysis

- Calculate an overall placement readiness score
- Evaluate CGPA
- Evaluate technical skills
- Consider projects and certifications
- Consider assessment performance
- Display strengths
- Identify areas that need improvement

### 4. Company & Job Role Management

- Add companies
- View companies
- Update company information
- Remove companies
- Add job roles
- Manage minimum CGPA requirements
- Define required skills
- Define preferred skills

### 5. Eligibility & Company Matching

- Check eligibility for a job role
- Compare student CGPA with job requirements
- Check required skills
- Consider assessment performance when available
- Calculate a matching score for eligible students
- Categorize the calculated match score
- Identify missing required skills

### 6. Reports & Statistics

- View placement statistics
- Generate student reports
- Generate company reports
- Display total students
- Display average CGPA
- Display total companies and job roles

### 7. Data Management

- Save student information to files
- Save company and job-role information to files
- Load stored data when the application starts
- Handle invalid input using validation and custom exceptions

---

## Technologies & Tools Used

### Programming Language
- Java

### Java Concepts Used
- Object-Oriented Programming
- Classes and Objects
- Inheritance
- Encapsulation
- Polymorphism
- Enums
- Collections Framework
  - `List`
  - `Set`
  - `ArrayList`
  - `HashSet`
- Exception Handling
- Custom Exceptions
- File Handling
- Input Validation
- Modular Package Design

### Development & Version Control Tools
- Java JDK
- Command Prompt / PowerShell
- Git
- GitHub

### Storage
- File-based storage using text files

No external database or web server is required.

---

## Project Structure

```text
SmartPlacementTracker/
│
├── src/
│   ├── model/
│   │   ├── User.java
│   │   ├── Student.java
│   │   ├── Admin.java
│   │   ├── Skill.java
│   │   ├── Project.java
│   │   ├── Certification.java
│   │   ├── Company.java
│   │   ├── JobRole.java
│   │   ├── Assessment.java
│   │   ├── PlacementResult.java
│   │   └── ReadinessReport.java
│   │
│   ├── service/
│   │   ├── StudentService.java
│   │   ├── CompanyService.java
│   │   ├── EligibilityEngine.java
│   │   ├── MatchingEngine.java
│   │   └── ReadinessAnalyzer.java
│   │
│   ├── io/
│   │   └── FileManager.java
│   │
│   ├── exception/
│   │   ├── InvalidStudentException.java
│   │   ├── InvalidCompanyException.java
│   │   └── EligibilityException.java
│   │
│   ├── util/
│   │   ├── InputValidator.java
│   │   └── ReportGenerator.java
│   │
│   └── Main.java
│
├── data/
│   ├── students.txt
│   └── companies.txt
│
├── tests/
│   └── TestCases.md
│
├── docs/
│   ├── architecture.md
│   ├── workflow.md
│   ├── use_case.md
│   ├── sequence_diagram.md
│   ├── report.md
│   └── screenshots/
│
├── README.md
└── statement.md
```

---

## Installation & Setup

### Prerequisites

Make sure the following are installed:

- Java JDK
- Git (required for repository/version control)

Verify Java installation:

```powershell
java -version
javac -version
```

---

## How to Run the Project

### Step 1: Open the Project Directory

Open Command Prompt or PowerShell and navigate to the project root directory.

Example:

```powershell
cd SmartPlacementTracker
```

### Step 2: Compile the Project

Run:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
```

The compiled `.class` files will be generated inside the `out` directory.

### Step 3: Run the Application

Run:

```powershell
java -cp out Main
```

The application will display the main menu:

```text
===== SMART PLACEMENT & SKILL TRACKER =====
1. Student
2. Placement Admin
3. Exit
```

Select the required option and follow the menu instructions.

---

## Application Workflow

### Student Workflow

```text
Start
  ↓
Student Menu
  ↓
Create / View / Update Profile
  ↓
Manage Skills, Projects & Certifications
  ↓
Check Placement Readiness
  ↓
Find Matching Companies
  ↓
View Skill Gaps
  ↓
Logout
```

### Placement Admin Workflow

```text
Start
  ↓
Placement Admin Menu
  ↓
View / Search Students
  ↓
Manage Companies
  ↓
Managee Job Roles
  ↓
View Placement Statistics
  ↓
Generate Reports
  ↓
Logout
```

---

## Testing

The application was tested using functional test cases covering the major features of the system.

### Testing Areas

- Student profile creation
- Invalid CGPA validation
- Invalid email validation
- Duplicate student ID validation
- Student profile update
- Companny creation
- Job role creation
- Eligibility checking
- Skill gap detection
- Company matching
- Placement readiness analysis
- File persistence
- Report generation
- Final project compilation

### Run / Verify Testing

Compile the project:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
```

Run the application:

```powershell
java -cp out Main
```

Follow the application menus and verify the expected output for each test case.

Detailed test cases and results are available in:

```text
tests/TestCases.md
```

---

## Screenshotss

Screenshots demonstrating the major features of the application are available in:

```text
docs/screenshots/
```

### Main Menu

![Main Menu](docs/screenshots/main_menu.png)

### Student Profile

![Student Profile](docs/screenshots/student_profile.png)

### Company Management

![Company Management](docs/screenshots/view_companies.png)

### Job Role Management

![Job Roles](docs/screenshots/job_roles.png)

### Placement Readiness

![Placement Readiness](docs/screenshots/readiness.png)

### Company Matching

![Company Matching](docs/screenshots/company_matching.png)

### Skill Gap Analysis

![Skill Gap Analysis](docs/screenshots/skill_gap.png)

### Placement Reports

![Placement Reports](docs/screenshots/placement_reports.png)

---

## Data Persistence

The appliication uses file-based storage.

Student information is stored in:

```text
data/students.txt
```

Company and job-role information is stored in:

```text
data/companies.txt
```

Stored data is loaded automatically when the application starts.

---

## Project Documentation

Additional documentation is available in the `docs/` folder:

- `architecture.md` — System Architecture
- `workflow.md` — Process Workflow
- `use_case.md` — Use Case Diagram
- `sequence_diagram.md` — Sequence Diagram
- `class_diagram.md` — UML Class Diagram
- `storage_design.md` — Storage Design
- `report.md` — Detailed Project Report

The project statement is available in:

```text
statement.md
```

---

## Error Handling & Validation

The application validates user input before performing operations.

Examples include:

- Empty students IDs are rejected
- Empty names are rejected
- Invalid emaiil addresses are rejected
- CGPA values outside the range 0–10 are rejected
- Invalid graduation years are rejected
- Duplicate student IDs are rejected
- Duplicate company IDs are rejected
- Invalid company and job-role operations are handled using custom exceptions

Custom exceptions are organized inside the:

```text
src/exception/
```

package.

---

## GitHub Repository

The project is maintained using Git for version control and is hosted on GitHub.

Repository:

https://github.com/vidushikesharwani/SmartPlacementTracker

---

## Future Enhancements

Possible future extensions include:

- Integration with a relational database
- Graphical or web-based interface
- Secure authentication and role-based access
- Advanced placement analytics
- Customizable matching criteria
- Additional assessment categories
- Report export to PDF or CSV
- Notifications for new job opportunities

---

## Author

**Vidushi Kesharwani**

Smart Placement & Skill Tracker  
A Java-Based Placement Readiness and Company Matching System