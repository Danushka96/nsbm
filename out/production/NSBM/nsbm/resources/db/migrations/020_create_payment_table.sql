CREATE TABLE payments(
  id varchar(8) PRIMARY KEY ,
  student_id varchar(9),
  subject_id varchar(8),
  reciept_id varchar(8),
  FOREIGN KEY (student_id) REFERENCES students(reg_Number),
  FOREIGN KEY (subject_id) REFERENCES subjects(code),
  FOREIGN KEY (reciept_id) REFERENCES reciept(id)
)