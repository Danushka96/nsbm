CREATE TABLE labs(
  id varchar(8) PRIMARY KEY,
  name varchar(20),
  numberofSeats int(2),
  faculty varchar(8),
  FOREIGN KEY (faculty) REFERENCES faculties(code)
)