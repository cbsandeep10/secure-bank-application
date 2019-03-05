-- noinspection SqlDialectInspectionForFile

DROP DATABASE bank;
CREATE DATABASE bank;

DROP TABLE IF EXISTS bank.account;
CREATE TABLE bank.account (
  account_no int(11) unsigned NOT NULL,
  user_id int(11) unsigned NOT NULL,
  balance decimal(10,2) NOT NULL,
  routing_no int(11) NOT NULL,
  account_type int(02) NOT NULL default 1,
  interest decimal(5,2),
  created date,
  updated date,
  PRIMARY KEY (account_no),
  FOREIGN KEY (user_id) REFERENCES bank.user(user_id)
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

INSERT INTO bank.user values(1,'Sandeep Balaji', 'M', CURDATE(), '4805775641', 'scbalaji@asu.edu', '2430 S MILL AVE, TEMPE',1,CURDATE() );
INSERT INTO bank.account VALUES (123,1, 53.0, 456, 1, 5.0, CURDATE() ,CURDATE() );

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



DROP TABLE IF EXISTS bank.auth_user_role;
DROP TABLE IF EXISTS bank.auth_role;
DROP TABLE IF EXISTS bank.auth_user;
CREATE TABLE bank.auth_role (
  auth_role_id int(11) NOT NULL AUTO_INCREMENT,
  role_name varchar(255) DEFAULT NULL,
  role_desc varchar(255) DEFAULT NULL,
  PRIMARY KEY (auth_role_id)
);
INSERT INTO bank.auth_role VALUES (1,'ADMIN','This user has ultimate rights for everything');
INSERT INTO bank.auth_role VALUES (2,'tier1','tier 1');
INSERT INTO bank.auth_role VALUES (3,'tier2','tier 2');
INSERT INTO bank.auth_role VALUES (4,'user','user');
INSERT INTO bank.auth_role VALUES (5,'merchant','merchant');


CREATE TABLE bank.auth_user (
  auth_user_id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  status varchar(255),
  PRIMARY KEY (auth_user_id)
);

CREATE TABLE bank.auth_user_role (
  auth_user_id int(11) NOT NULL,
  auth_role_id int(11) NOT NULL,
  PRIMARY KEY (auth_user_id,auth_role_id),
  KEY FK_user_role (auth_role_id),
  CONSTRAINT FK_auth_user FOREIGN KEY (auth_user_id) REFERENCES auth_user (auth_user_id),
  CONSTRAINT FK_auth_user_role FOREIGN KEY (auth_role_id) REFERENCES auth_role (auth_role_id)
) ;

insert into bank.auth_user (auth_user_id,first_name,last_name,email,password,status) values (1,'Ankit','Wasankar','admin@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED');
insert into bank.auth_user_role (auth_user_id, auth_role_id) values ('1','1');
insert into bank.auth_user_role (auth_user_id, auth_role_id) values ('1','2');
insert into bank.auth_user_role (auth_user_id, auth_role_id) values ('1','3');
insert into bank.auth_user_role (auth_user_id, auth_role_id) values ('1','4');
insert into bank.auth_user_role (auth_user_id, auth_role_id) values ('1','5');

