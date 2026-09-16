# System Architecture

```text
                 SMART PLACEMENT & SKILL TRACKER
                              |
                              v
                    +-------------------+
                    |   Main / CLI      |
                    |   User Interface  |
                    +---------+---------+
                              |
              +---------------+---------------+
              |                               |
              v                               v
     +-------------------+           +-------------------+
     | Student Module    |           | Admin Module      |
     +---------+---------+           +---------+---------+
               |                               |
               +---------------+---------------+
                               |
                               v
                    +-------------------+
                    |   Service Layer   |
                    +-------------------+
                    | StudentService    |
                    | CompanyService    |
                    | MatchingEngine     |
                    | EligibilityEngine  |
                    | ReadinessAnalyzer  |
                    +---------+---------+
                              |
                              v
                    +-------------------+
                    |    Model Layer    |
                    +-------------------+
                    | Student           |
                    | Admin             |
                    | Skill             |
                    | Project           |
                    | Certification     |
                    | Company           |
                    | JobRole           |
                    | Assessment        |
                    | PlacementResult   |
                    | ReadinessReport   |
                    +---------+---------+
                              |
                +-------------+-------------+
                |                           |
                v                           v
       +-------------------+       +-------------------+
       | File Management   |       | Validation &      |
       | FileManager       |       | Exceptions        |
       +---------+---------+       +-------------------+
                 |
                 v
          +-------------+
          | data/ files |
          +-------------+