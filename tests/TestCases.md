# Test Cases

| Test ID | Test Case | Input | Expected Result |
|--------|-----------|-------|-----------------|
| TC01 | Create student | Valid student details | Student profile created successfully |
| TC02 | Invalid CGPA | CGPA = 12 | Error message displayed |
| TC03 | Duplicate student ID | Existing student ID | Student ID already exists message |
| TC04 | Update student | Valid updated details | Student profile updated |
| TC05 | Add company | Valid company details | Company added successfully |
| TC06 | Add job role | Valid job details | Job role added successfully |
| TC07 | Check eligibility | Student meets all requirements | Student marked eligible |
| TC08 | Check ineligibility | Missing required skill | Student marked ineligible |
| TC09 | Find company match | Eligible student and job role | Match score displayed |
| TC10 | View skill gaps | Student missing required skills | Missing skills displayed |
| TC11 | Placement readiness | Student profile with data | Readiness score generated |
| TC12 | Save data | Student/company information | Data stored in files |
| TC13 | Load data | Existing data files | Data loaded at startup |
| TC14 | Search student | Valid student ID | Student details displayed |
| TC15 | Generate reports | Existing student/company data | Placement reports displayed |