CREATE TABLE undergraduates(
  student_id varchar(20) PRIMARY KEY NOT NULL,
  rank INT(3),
  stream VARCHAR(20),
  reg_number varchar(9),
  FOREIGN KEY (reg_number) REFERENCES students(reg_Number)
);