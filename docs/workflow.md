# System Workflow

```text
                     START
                       |
                       v
              Main Application
                       |
                       v
              Select User Role
                 /           \
                /             \
               v               v
          STUDENT             ADMIN
             |                  |
             v                  v
      Student Menu        Admin Menu
             |                  |
     +-------+-------+    +-----+------+
     |       |       |    |            |
     v       v       v    v            v
   Profile Skills  Projects       Manage Companies
     |       |       |             & Job Roles
     |       v       v                  |
     |   Certifications                 v
     |       |                    Reports &
     |       |                    Statistics
     v       v
 Placement Readiness
     |
     v
 Eligibility Check
     |
     v
 Matching Engine
     |
     v
 Skill Gap Analysis
     |
     v
 Display Results
     |
     v
    Logout
     |
     v
    END