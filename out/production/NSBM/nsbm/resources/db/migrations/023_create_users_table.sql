CREATE TABLE users (
  username varchar(10) NOT NULL PRIMARY KEY,
  password varchar(64) NOT NULL,
  faculty_id varchar(8) NOT NULL,
  FOREIGN KEY (faculty_id) REFERENCES faculties(code)
);
