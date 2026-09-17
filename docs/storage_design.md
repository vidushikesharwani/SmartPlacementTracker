# Storage Design

The Smart Placement & Skill Tracker uses file-based storage instead of a relational database. The data is stored in text files inside the `data/` directory.

## Student Storage

File:

`data/students.txt`

Each student record is stored using the following format:

Student ID | Name | Email | Branch | CGPA | Graduation Year

Example:

S101|Vidushi Kesharwani|vidushi@example.com|CSE|8.8|2027

## Company and Job Role Storage

File:

`data/companies.txt`

Each record stores company and job-role information using the following format:

Company ID | Company Name | Industry | Job ID | Role Name | Minimum CGPA | Required Skills | Preferred Skills

Example:

C101|TCS|IT|J101|Software Developer|7.5|Java|SQL

## Storage Relationships

```text
Student
   |
   | stored in
   ↓
students.txt


Company
   |
   └── JobRole
          |
          | stored in
          ↓
     companies.txt