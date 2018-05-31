
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `students` (
  student_id varchar(9),
  intake_number int(2),
  registration_date date,
  nic varchar(10)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`),
  ADD FOREIGN KEY (nic) references universitymembers(nic);
COMMIT;
