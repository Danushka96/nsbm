CREATE TABLE assignments(
  id varchar(8) PRIMARY KEY,
  name varchar(20),
  marks int(2),
  subject_id varchar(8),
  type int(1),
  semester_id varchar(8),
  FOREIGN KEY (subject_id) REFERENCES subjects(code),
  FOREIGN KEY (semester_id) REFERENCES semester(id)
)