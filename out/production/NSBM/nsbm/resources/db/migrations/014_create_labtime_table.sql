CREATE TABLE labtime(
  lab_id varchar(8),
  timeslot_id varchar(8),
  instructor_id varchar(8),
  PRIMARY KEY (lab_id,timeslot_id),
  FOREIGN KEY (lab_id) REFERENCES labs(id),
  FOREIGN KEY (timeslot_id) REFERENCES timeslots(id),
  FOREIGN KEY (instructor_id) REFERENCES instructors(id)
);