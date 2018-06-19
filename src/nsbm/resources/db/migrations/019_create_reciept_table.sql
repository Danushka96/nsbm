CREATE TABLE reciept(
  id int(5) PRIMARY KEY AUTO_INCREMENT,
  issued_date date,
  amount double(7,2) default 0
)