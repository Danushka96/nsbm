CREATE TABLE timeslots(
  id varchar(8) PRIMARY KEY,
  fromtime time,
  totime time,
  dateof date,
  subject_id varchar(8),
  FOREIGN KEY (subject_id) REFERENCES subjects(code)
);