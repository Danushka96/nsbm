CREATE TABLE subjects(
  code varchar(8) PRIMARY KEY,
  name varchar(25),
  fee decimal(7,2),
  numberofCredits int(1),
  numberofHours int(3),
  lecturer_id varchar(8),
  course_id varchar(8),
  FOREIGN KEY (lecturer_id) REFERENCES lecturers(id),
  FOREIGN KEY (course_id) REFERENCES courses(code)
);