-- noinspection SqlDialectInspectionForFile

CREATE DATABASE bank;

DROP TABLE IF EXISTS bank.account;
CREATE TABLE bank.account (
  account_no int(11) unsigned NOT NULL,
  user_id int(11) NOT NULL,
  balance decimal(10,2) NOT NULL,
  routing_no int(11) NOT NULL,
  account_type int(2) NOT NULL,
  interest decimal(5,2),
  created date,
  updated date,
  PRIMARY KEY (account_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS bank.user;
CREATE TABLE bank.user (
  user_id int(11) unsigned NOT NULL,
  name varchar(256) NOT NULL,
  gender varchar(2) NOT NULL,
  dob date NOT NULL,
  contact varchar(12) NOT NULL,
  email_id varchar(256) NOT NULL,
  address varchar(256) NOT NULL,
  user_type int(2) NOT NULL,
  created date,
  PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS bank.transaction;
CREATE TABLE bank.transaction (
  transfer_id int(11) unsigned NOT NULL,
  transfer_amount decimal(10,2) NOT NULL,
  transfer_date date,
  description varchar(256),
  status int(1),
  from_account int(11),
  to_account int(11),
  PRIMARY KEY (transfer_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

