/*

use mysql;


create database csm;

-- 2. create a user csm-admin, with password 'csm-admin', and grant the user
--    all privileges on the database csm. 

grant all privileges on csm.* to 'csm-admin'@'localhost'
	identified by 'csm-admin' with grant option;

flush privileges;

*/

-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS users (
  id INT NOT NULL auto_increment,
  username VARCHAR(45) ,
  password VARCHAR(45) ,
  first_name VARCHAR(45) NULL ,
  last_name VARCHAR(45) NULL ,
  cin  INT NOT NULL,
  PRIMARY KEY (id)
 );

-- -----------------------------------------------------
-- Insert into users
-- -----------------------------------------------------

insert into users values (null, 'gpetros','student123','Gayaneh','Petrossian',999666333);
insert into users values (null, 'ebayazian','student123','Elnaz','Bayazian',123456789);
insert into users values (null, 'eliao','student123','Eric','Liao',888444222);
insert into users values (null, 'ssaeedi','student123','Saman','Saeedi',555666777);