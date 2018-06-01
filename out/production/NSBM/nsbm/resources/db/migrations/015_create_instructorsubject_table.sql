CREATE TABLE instructorsubject(
  instructor_id varchar(8),
  subject_id varchar(8),
  PRIMARY KEY (instructor_id,subject_id),
  FOREIGN KEY (instructor_id) REFERENCES instructors(id)
)