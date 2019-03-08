-- noinspection SqlDialectInspectionForFile

DROP DATABASE bank;
CREATE DATABASE bank;

DROP TABLE IF EXISTS bank.user;
CREATE TABLE bank.user (
  user_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(256) NOT NULL,
  gender varchar(2) NOT NULL,
  dob date NOT NULL,
  contact varchar(12) NOT NULL,
  email_id varchar(256) NOT NULL,
  address varchar(256) NOT NULL,
  user_type int(2) NOT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE bank.user AUTO_INCREMENT=1000;

DROP TABLE IF EXISTS bank.employee;
CREATE TABLE bank.employee
(
    employee_id int(11) unsigned NOT NULL AUTO_INCREMENT,
    employee_name varchar(256) NOT NULL,
    gender varchar(256) NOT NULL,
    age int(11) ,
    tier_level int(11) unsigned NOT NULL,
    designation_id int(11),
    contact_no varchar(256),
    email_id varchar(256),
    address varchar(256),
    created timestamp DEFAULT CURRENT_TIMESTAMP(),
    updated timestamp DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (employee_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE bank.employee AUTO_INCREMENT=1000;

INSERT INTO bank.employee (employee_id, employee_name,gender,age, tier_level, designation_id,contact_no,email_id,address)
VALUES (1,"abc","M",23,1,1,"452-345-6789","abc@gmail.com","Tempe,AZ");
INSERT INTO bank.employee (employee_id, employee_name,gender,age, tier_level, designation_id,contact_no,email_id,address)
VALUES (2,"def","M",25,2,2,"408-345-6789","def@gmail.com","Tempe,AZ");
INSERT INTO bank.employee (employee_id, employee_name,gender,age, tier_level, designation_id,contact_no,email_id,address)
VALUES (3,"inter","M",24,3,1,"402-345-6789","inter@gmail.com","Tempe,AZ");

DROP TABLE IF EXISTS bank.account;
CREATE TABLE bank.account (
  account_no int(11) unsigned NOT NULL AUTO_INCREMENT,
  user_id int(11) unsigned NOT NULL,
  balance decimal(10,2) NOT NULL,
  routing_no int(11) NOT NULL,
  account_type int(2) NOT NULL,
  interest decimal(5,2),
  created timestamp DEFAULT CURRENT_TIMESTAMP(),
  updated timestamp DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (account_no),
  FOREIGN KEY (user_id) REFERENCES bank.user(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE bank.account AUTO_INCREMENT=1000;

DROP TABLE IF EXISTS  bank.transaction_request;
CREATE TABLE bank.`transaction_request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP(),
  `status_id` int(11) NOT NULL,
  `created_by` int(11) unsigned NOT NULL,
  `approved_by` int(11) unsigned,
  `approved_at` timestamp,
  `from_account` int(11) unsigned NOT NULL,
  `to_account` int(11) unsigned NOT NULL,
  `transaction_amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`request_id`),
  FOREIGN KEY (`approved_by`) REFERENCES bank.employee(employee_id),
  FOREIGN KEY (`created_by`) REFERENCES bank.user(user_id),
  FOREIGN KEY (from_account) REFERENCES bank.account(account_no),
  FOREIGN KEY (to_account) REFERENCES bank.account(account_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS bank.transaction;
CREATE TABLE bank.transaction (
  transaction_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  transaction_amount decimal(10,2) NOT NULL,
  transaction_timestamp timestamp DEFAULT CURRENT_TIMESTAMP(),
  transaction_type int(1) NOT NULL,
  description varchar(256),
  status int(1),
  account_no int(11) unsigned NOT NULL,
  balance decimal(10,2),
  request_id int(11),
  PRIMARY KEY (transaction_id),
  FOREIGN KEY (account_no) REFERENCES bank.account(account_no),
  FOREIGN KEY (request_id) REFERENCES bank.transaction_request(request_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO bank.user (name, gender,dob, contact,email_id, address,user_type) values('Sandeep Balaji', 'M', CURRENT_TIMESTAMP(), '4805775641', 'scbalaji@asu.edu', '2430 S MILL AVE, TEMPE',1 );
INSERT INTO bank.account(user_id,balance ,routing_no,account_type, interest) VALUES (1000, 5000.0, 45612, 1, 5.0);
INSERT INTO bank.account(user_id,balance ,routing_no,account_type, interest) VALUES (1000, 2500.0, 45622, 2, 12.0);


INSERT INTO `bank`.`transaction_request` (`request_type`, `status_id`,`created_by`,`approved_by`,from_account,to_account,transaction_amount) VALUES(1,0,1000,1,1000,1001,200.0);

INSERT INTO bank.transaction (transaction_amount,transaction_type,description,status,account_no,request_id)values(200.0,1,'Just transfering',0,1000,1);
INSERT INTO bank.transaction (transaction_amount,transaction_type,description,status,account_no,request_id)values(200.0,2,'Just transfering',0,1001,1);


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


DROP TABLE IF EXISTS bank.admin_log;
CREATE TABLE bank.admin_log (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  type_id int(11) NOT NULL,
  log_timestamp timestamp ,
  related_user_id int(11) unsigned NOT NULL,
  message varchar(256),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO bank.admin_log (id, type_id,log_timestamp,related_user_id,message)
VALUES (1,2,'2019-03-02 00:00:00',3,"Transaction Success");
INSERT INTO bank.admin_log (id, type_id,log_timestamp,related_user_id,message)
VALUES (2,3,'2019-03-02 00:00:00',4,"Transaction Failure");
INSERT INTO bank.admin_log (id, type_id,log_timestamp,related_user_id,message)
VALUES (3,4,'2019-03-02 00:00:00',5,"Transaction Success");


