CREATE DATABASE IF NOT EXISTS healthy_db;
USE healthy_db;

CREATE TABLE IF NOT EXISTS user (
  userId int(11) NOT NULL AUTO_INCREMENT,
  firstName varchar(100) NOT NULL,
  lastName varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  age int(11) NOT NULL,
  gender varchar(1) NOT NULL,
  height float(11) NOT NULL,
  lifestyle varchar(100) NOT NULL,
  PRIMARY KEY (userId),
  UNIQUE KEY email_UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;