CREATE TABLE postgraduates(
  student_id varchar(20) NOT NULL PRIMARY KEY,
  qualification_type varchar(30),
  institute VARCHAR(40),
  yearof_Completion varchar(4),
  reg_number varchar(9),
  course_id varchar(8),
  FOREIGN KEY (reg_number) REFERENCES students(reg_Number),
  FOREIGN KEY (course_id) REFERENCES courses(code)
)