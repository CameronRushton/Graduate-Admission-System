INSERT INTO privilege (id, operation, owner, target) VALUES
    (0, 'CREATE', 'SELF', 'APPLICATION'),
    (1, 'READ', 'SELF', 'APPLICATION'),
    (2, 'UPDATE', 'SELF', 'APPLICATION'),
    (3, 'DELETE', 'SELF', 'APPLICATION'),
    (4, 'CREATE', 'SELF', 'INTEREST'),
    (5, 'READ', 'SELF', 'INTEREST'),
    (6, 'UPDATE', 'SELF', 'INTEREST'),
    (7, 'DELETE', 'SELF', 'INTEREST'),
    (8, 'READ', 'ALL', 'TERM'),
    (9, 'CREATE', 'ALL', 'TERM'),
    (10, 'UPDATE', 'ALL', 'TERM'),
    (11, 'DELETE', 'ALL', 'TERM'),
    (12, 'READ', 'ALL_STUDENTS', 'APPLICATION'),
    (13, 'READ', 'ALL_STUDENTS', 'INTEREST'),
    (14, 'READ', 'ALL_PROFS', 'INTEREST');

INSERT INTO role (role_name) VALUES
    ('STUDENT'),
    ('PROFESSOR'),
    ('ADMIN');

INSERT INTO role_privileges (role_role_name, privileges_id) VALUES
    ('STUDENT', 0),
    ('STUDENT', 1),
    ('STUDENT', 2),
    ('STUDENT', 3),
    ('STUDENT', 4),
    ('STUDENT', 5),
    ('STUDENT', 6),
    ('STUDENT', 7),
    ('STUDENT', 8),
    ('PROFESSOR', 4),
    ('PROFESSOR', 5),
    ('PROFESSOR', 6),
    ('PROFESSOR', 7),
    ('PROFESSOR', 8),
    ('PROFESSOR', 12),
    ('ADMIN', 8),
    ('ADMIN', 9),
    ('ADMIN', 10),
    ('ADMIN', 11),
    ('ADMIN', 12),
    ('ADMIN', 13),
    ('ADMIN', 14);

INSERT INTO user_account (id, email, first_name, last_name, role_role_name) VALUES
    (0, 'gas.student4806@gmail.com', 'Parker', 'Peter', 'STUDENT'),
    (1, 'gas.prof4806@gmail.com', 'Wayne', 'Bruce', 'PROFESSOR'),
    (2, 'gas.staff4806@gmail.com', 'Kent', 'Clark', 'ADMIN');

INSERT INTO term (term_id, active, deadline, season, year) VALUES
    (0, true, '2020-08-15', 'FALL', 2020),
    (1, true, '2020-12-15', 'WINTER', 2021);

INSERT INTO interest (id, department, keyword) VALUES
    (0, 'SYSC', 'cybersecurity'),
    (1, 'MAAE', 'gears'),
    (2, 'SREE', 'grilled cheese'),
    (3, 'SYSC', 'machine learning'),
    (4, 'SYSC', 'distributed systems');

INSERT INTO user_account_interests (user_account_id, interests_id) values
    (0, 3),
    (0, 0),
    (1, 3),
    (1, 4);

INSERT INTO application (id, degree, department, gpa, resume_file_name, status, term_term_id) VALUES
    (0, 'MaSc', 'SYSC', 4.0, 'resume.txt', 'SUBMITTED', 0),
    (1, 'MaSc', 'SYSC', 4.0, 'resume2.txt', 'SUBMITTED', 1);

INSERT INTO application_professors (application_id, professors_id) VALUES
    (0, 1);

INSERT INTO user_account_applications (user_account_id, applications_id) VALUES
    (0, 0);
