CREATE TABLE courses(
  code VARCHAR(8) PRIMARY KEY,
  name varchar(60),
  credits int(2),
  numberofyears int(1),
  can_extend int(1),
  faculty varchar(8),
  FOREIGN KEY (faculty) REFERENCES faculties(code)
);