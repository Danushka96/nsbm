
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `students` (
  `id` varchar(8) NOT NULL,
  `name` varchar(40) NOT NULL,
  `nic` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `address` varchar(60) NOT NULL,
  `tp` varchar(10) NOT NULL,
  `dateofbirth` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nic` (`nic`,`email`);
COMMIT;
