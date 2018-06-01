create table universityMembers(
  nic varchar(10) NOT NULL primary key ,
  firstName varchar(20),
  lastName varchar(20),
  gender varchar(1),
  email varchar(30) UNIQUE,
  dob date,
  mobile varchar(10),
  address varchar(50)
);
