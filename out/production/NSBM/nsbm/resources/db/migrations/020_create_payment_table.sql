CREATE TABLE payments(
  id int(8) PRIMARY KEY AUTO_INCREMENT,
  student_id varchar(9),
  semster_id varchar(8),
  reciept_id int(5),
  FOREIGN KEY (student_id) REFERENCES students(reg_Number),
  FOREIGN KEY (semster_id) REFERENCES semester(id),
  FOREIGN KEY (reciept_id) REFERENCES reciept(id)
)