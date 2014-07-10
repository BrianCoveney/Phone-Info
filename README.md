Phone-Info
==========

This is a CRUD application created with Java, which is used
in conjunction with the below MySql tables.


CREATE TABLE `calls` (
  `name` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `contact` (
  `name` varchar(255) NOT NULL,
  `PhoneNumber` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
