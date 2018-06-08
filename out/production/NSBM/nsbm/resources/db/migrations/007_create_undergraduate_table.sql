CREATE TABLE undergraduates(
  student_id varchar(20) PRIMARY KEY NOT NULL,
  rank INT(3),
  stream VARCHAR(20),
  reg_number varchar(15),
  course_id varchar(8),
  FOREIGN KEY (reg_number) REFERENCES students(reg_Number),
  FOREIGN KEY (course_id) REFERENCES courses(code)
);