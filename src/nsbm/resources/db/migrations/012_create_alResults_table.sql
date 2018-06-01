CREATE TABLE alresults(
  student_id varchar(20),
  subject_name varchar(30),
  result varchar(1),
  PRIMARY KEY (student_id,subject_name),
  FOREIGN KEY (student_id) REFERENCES undergraduates(student_id)
);