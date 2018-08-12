CREATE TABLE studentassignments(
  assignment_id varchar(8),
  student_id varchar(15),
  marks int(2),
  PRIMARY KEY (assignment_id,student_id),
  FOREIGN KEY (assignment_id) REFERENCES assignments(id),
  FOREIGN KEY (student_id) REFERENCES students(reg_Number)
)