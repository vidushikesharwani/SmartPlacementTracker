# Class Diagram

```mermaid
classDiagram

class User {
    -String userId
    -String name
    -String email
    +User(String userId, String name, String email)
    +getUserId() String
    +getName() String
    +getEmail() String
    +setName(String name)
    +setEmail(String email)
}

class Student {
    -String branch
    -double cgpa
    -int graduationYear
    -Set~Skill~ skills
    -List~Project~ projects
    -List~Certification~ certifications
    +addSkill(Skill skill)
    +addProject(Project project)
    +addCertification(Certification certification)
    +displayStudent()
}

class Admin {
    +Admin(String adminId, String name, String email)
    +displayAdmin()
}

class Skill {
    -String skillName
    -ProficiencyLevel proficiencyLevel
    +getSkillName() String
    +getProficiencyLevel() ProficiencyLevel
    +setProficiencyLevel(ProficiencyLevel level)
}

class Project {
    -String projectId
    -String projectName
    -String description
    -Set~String~ technologies
    +addTechnology(String technology)
}

class Certification {
    -String certificationId
    -String name
    -String issuingOrganization
    -int year
}

class Company {
    -String companyId
    -String companyName
    -String industry
    -List~JobRole~ jobRoles
    +addJobRole(JobRole jobRole)
}

class JobRole {
    -String jobId
    -String roleName
    -double minimumCGPA
    -Set~String~ requiredSkills
    -Set~String~ preferredSkills
    +addRequiredSkill(String skill)
    +addPreferredSkill(String skill)
}

class Assessment {
    -String assessmentId
    -String studentId
    -double dsaScore
    -double javaScore
    -double aptitudeScore
    -double communicationScore
    +getAverageScore() double
}

class PlacementResult {
    -String studentId
    -String companyId
    -String jobId
    -boolean eligible
    -double cgpaScore
    -double requiredSkillScore
    -double preferredSkillScore
    -double profileScore
    -double overallMatchScore
    -Set~String~ missingSkills
}

class ReadinessReport {
    -String studentId
    -double cgpaScore
    -double skillScore
    -double projectScore
    -double certificationScore
    -double assessmentScore
    -double overallScore
    -List~String~ strengths
    -List~String~ improvementAreas
}

class StudentService
class CompanyService
class EligibilityEngine
class MatchingEngine
class ReadinessAnalyzer
class FileManager
class InputValidator
class ReportGenerator

class InvalidStudentException
class InvalidCompanyException
class EligibilityException

User <|-- Student
User <|-- Admin

Student "1" o-- "*" Skill
Student "1" o-- "*" Project
Student "1" o-- "*" Certification
Student "1" -- "0..*" Assessment

Company "1" o-- "*" JobRole

StudentService --> Student
CompanyService --> Company
CompanyService --> JobRole

EligibilityEngine --> Student
EligibilityEngine --> JobRole
EligibilityEngine --> Assessment

MatchingEngine --> EligibilityEngine
MatchingEngine --> Student
MatchingEngine --> JobRole
MatchingEngine --> Assessment
MatchingEngine --> PlacementResult

ReadinessAnalyzer --> Student
ReadinessAnalyzer --> Assessment
ReadinessAnalyzer --> ReadinessReport

FileManager --> Student
FileManager --> Company

ReportGenerator --> Student
ReportGenerator --> Company

StudentService ..> InvalidStudentException
CompanyService ..> InvalidCompanyException
EligibilityEngine ..> EligibilityException