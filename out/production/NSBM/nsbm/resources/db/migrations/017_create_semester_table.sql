CREATE TABLE semester(
  id varchar(8),
  faculty varchar(8),
  semesternumber varchar(2),
  yearof varchar(4),
  start_date date,
  end_date date,
  PRIMARY KEY (id,faculty),
  FOREIGN KEY (faculty) REFERENCES faculties(code)
)