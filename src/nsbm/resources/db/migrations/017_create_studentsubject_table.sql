CREATE TABLE studentsubject(
  sem_id varchar(8),
  student_id varchar(9),
  subject_id varchar(8),
  PRIMARY KEY (sem_id,subject_id,student_id),
  FOREIGN KEY (sem_id) REFERENCES semester(id),
  FOREIGN KEY (student_id) REFERENCES students(reg_Number),
  FOREIGN KEY (subject_id) REFERENCES subjects(code)
)