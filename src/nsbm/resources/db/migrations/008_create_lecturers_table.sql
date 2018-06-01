CREATE TABLE lecturers(
  id varchar(8) PRIMARY KEY,
  researchSpeciality varchar(20),
  office_number varchar(4),
  nic varchar(11),
  FOREIGN KEY (nic) REFERENCES universitymembers(nic)
);