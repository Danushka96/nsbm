CREATE TABLE timeslots(
  id varchar(8),
  course_id varchar(8),
  faculty_id varchar(8),
  fromtime time,
  totime time,
  dateof varchar(10),
  subject_id varchar(8),
  PRIMARY KEY (id,course_id,faculty_id),
  FOREIGN KEY (course_id) REFERENCES courses(code),
  FOREIGN KEY (faculty_id) REFERENCES faculties(code),
  FOREIGN KEY (subject_id) REFERENCES subjects(code)
);