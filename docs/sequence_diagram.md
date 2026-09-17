# Sequence Diagram

The following sequence diagram represents the company-matching process in the Smart Placement & Skill Tracker.

```mermaid
sequenceDiagram
    actor Student
    participant Main
    participant MatchingEngine
    participant EligibilityEngine
    participant StudentProfile
    participant JobRole
    participant PlacementResult

    Student->>Main: Select Find Matching Companies
    Main->>StudentProfile: Get student profile
    Main->>JobRole: Get job role details
    Main->>MatchingEngine: Calculate match

    MatchingEngine->>EligibilityEngine: Check eligibility
    EligibilityEngine->>StudentProfile: Check CGPA and skills
    EligibilityEngine->>JobRole: Get requirements
    EligibilityEngine-->>MatchingEngine: Eligibility result

    alt Student is eligible
        MatchingEngine->>StudentProfile: Get profile details
        MatchingEngine->>JobRole: Get required and preferred skills
        MatchingEngine->>PlacementResult: Calculate match scores
        PlacementResult-->>MatchingEngine: Match result
        MatchingEngine-->>Main: Return placement result
        Main-->>Student: Display company match
    else Student is not eligible
        MatchingEngine-->>Main: Return eligibility reason
        Main-->>Student: Display ineligibility reason
    end