CREATE TABLE studentassignments(
  assignment_id varchar(8),
  student_id varchar(9),
  marks int(2),
  PRIMARY KEY (assignment_id,student_id)
)