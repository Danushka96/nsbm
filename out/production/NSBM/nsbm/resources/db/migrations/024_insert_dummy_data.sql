INSERT INTO faculties(code, name, telephone) VALUES ('SOB','School of Business','0112506585');
INSERT INTO faculties(code, name, telephone) VALUES ('SOC', 'School of Computing','0115865235');
INSERT INTO faculties(code, name, telephone) VALUES ('SOE', 'School of Engineering','0115865235');

INSERT INTO users(username, password, faculty_id) VALUES ('danushka','1234','SOB');

INSERT INTO courses(code, name, credits, numberofyears, can_extend, faculty) VALUES ('MIT','Management Information',34,3,1,'SOC');
INSERT INTO courses(code, name, credits, numberofyears, can_extend, faculty) VALUES ('SE','Software Engineering',34,3,1,'SOC');
INSERT INTO courses(code, name, credits, numberofyears, can_extend, faculty) VALUES ('MM','Multimedia',34,3,1,'SOC');
INSERT INTO courses(code, name, credits, numberofyears, can_extend, faculty) VALUES ('CS','Computer Science',34,3,1,'SOC');
INSERT INTO courses(code, name, credits, numberofyears, can_extend, faculty) VALUES ('CN','Computer Networks',34,3,1,'SOC');

INSERT INTO courses(code, name, credits, numberofyears, can_extend, faculty) VALUES ('BM','Business Management',34,3,1,'SOB');
INSERT INTO courses(code, name, credits, numberofyears, can_extend, faculty) VALUES ('BC','Business Management',34,3,1,'SOB');
INSERT INTO courses(code, name, credits, numberofyears, can_extend, faculty) VALUES ('AF','Accounting and Finance',34,3,1,'SOB');
INSERT INTO courses(code, name, credits, numberofyears, can_extend, faculty) VALUES ('ID','Interior Design',34,3,1,'SOB');

INSERT INTO courses(code, name, credits, numberofyears, can_extend, faculty) VALUES ('CSE','Computer System Engineering',34,3,1,'SOE');