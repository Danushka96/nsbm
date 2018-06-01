CREATE TABLE instructors(
  id varchar(8) PRIMARY KEY,
  nic varchar(11),
  FOREIGN KEY (nic) REFERENCES universitymembers(nic)
);