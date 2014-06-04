/*

use mysql;


create database csm;

-- 2. create a user csm-admin, with password 'csm-admin', and grant the user
--    all privileges on the database csm.

grant all privileges on csm.* to 'csm-admin'@'localhost'
	identified by 'csm-admin' with grant option;

flush privileges;

*/

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema csm
-- -----------------------------------------------------
-- schema model for CSM database
DROP SCHEMA IF EXISTS `csm` ;
CREATE SCHEMA IF NOT EXISTS `csm` DEFAULT CHARACTER SET utf8 ;

USE `csm` ;

-- -----------------------------------------------------
-- Table `csm`.`users`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `csm`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `cin` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `csm`.`department`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `csm`.`department` (
  `id` INT NOT NULL auto_increment,
  `name` VARCHAR(80) NULL,
  `phone` VARCHAR(20) NULL,
  `department_abbr` VARCHAR(5) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `csm`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csm`.`courses` (
  `id` INT NOT NULL auto_increment,
  `subject` VARCHAR(10) NULL,
  `number` SMALLINT NULL,
  `description` VARCHAR(45) NULL,
  `credit` TINYINT(1) NULL,
  `units` SMALLINT NULL,
  `type` VARCHAR(10) NULL,
  `career` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  `departments_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_courses_departments1`
    FOREIGN KEY (`departments_id`)
    REFERENCES `csm`.`department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_courses_departments_idx` ON `csm`.`courses` (`departments_id` ASC);




-- -----------------------------------------------------
-- Table `csm`.`terms`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `csm`.`terms` (
  `id` INT NOT NULL auto_increment,
  `description` VARCHAR(45) NULL,
  `status` TINYINT(1) NULL,
primary key ( `id`)
)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `csm`.`schedule`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS `csm`.`schedule` (
  `id` INT NOT NULL auto_increment,
  `term` INT NOT NULL,
  `class_number`  int NULL,
  `section` SMALLINT NULL,
  `component` VARCHAR(45) NULL,
  `room` VARCHAR(45) NULL,
  `start_date` DATEtime NULL,
  `end_date` DATEtime NULL,
  `status` TINYINT(1) NULL,
  `courses_id` INT NOT NULL,
   `capacity` INT NULL,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`id`, `courses_id`, `department_id`, `term`),
  CONSTRAINT `fk_schedule_courses`
    FOREIGN KEY (`courses_id`)
    REFERENCES `csm`.`courses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_schedule_terms1`
    FOREIGN KEY (`term`)
    REFERENCES `csm`.`terms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 CONSTRAINT `fk_schedule_departments1`
    FOREIGN KEY (`department_id`)
    REFERENCES `csm`.`department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_schedule_courses_idx` ON `csm`.`schedule` (`courses_id` ASC);

CREATE INDEX `fk_schedule_terms1_idx` ON `csm`.`schedule` (`term` ASC);

CREATE INDEX `fk_schedule_departments1_idx` ON `csm`.`schedule` (`department_id` ASC);


-- -----------------------------------------------------
-- Table `csm`.`enrollment`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `csm`.`enrollment` (
  `id` INT NOT NULL auto_increment,
  `term` INT NOT NULL,
  `status` VARCHAR(45) NULL,
  `schedule_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`, `users_id`, `term`),
  CONSTRAINT `fk_enrollment_schedule1`
    FOREIGN KEY (`schedule_id`)
    REFERENCES `csm`.`schedule` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_enrollment_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `csm`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_enrollment_terms1`
    FOREIGN KEY (`term`)
    REFERENCES `csm`.`terms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_enrollment_schedule1_idx` ON `csm`.`enrollment` (`schedule_id` ASC);

CREATE INDEX `fk_enrollment_users1_idx` ON `csm`.`enrollment` (`users_id` ASC);

CREATE INDEX `fk_enrollment_terms1_idx` ON `csm`.`enrollment` (`term` ASC);


-- -----------------------------------------------------
-- Table `csm`.`faculty`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `csm`.`faculty` (
  `id` INT NOT NULL auto_increment,
  `title` varchar(5) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `office_room_number` VARCHAR(45) NULL,
  `office_phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `web_url` VARCHAR(100) NULL,
  `departments_id` INT NOT NULL,
  PRIMARY KEY (`id`, `departments_id`),
  CONSTRAINT `fk_faculty_departments1`
    FOREIGN KEY (`departments_id`)
    REFERENCES `csm`.`department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE INDEX `fk_faculty_departments1_idx` ON `csm`.`faculty` (`departments_id` ASC);


-- -----------------------------------------------------
-- Table `csm`.`watchlist`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS `csm`.`watchlist` (
  `id` INT NOT NULL auto_increment,
  `users_id` INT NOT NULL,
  `term` INT NOT NULL,
  `schedule_id` INT NOT NULL,
  `schedule_courses_id` INT NOT NULL,
  `schedule_department_id` INT NOT NULL,
  `schedule_term` INT NOT NULL,
  PRIMARY KEY (`id`, `users_id`, `term`),
  CONSTRAINT `fk_enrollment_users10`
    FOREIGN KEY (`users_id`)
    REFERENCES `csm`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_watchlist_terms1`
    FOREIGN KEY (`term`)
    REFERENCES `csm`.`terms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_watchlist_schedule1`
    FOREIGN KEY (`schedule_id` , `schedule_courses_id` , `schedule_department_id` , `schedule_term`)
    REFERENCES `csm`.`schedule` (`id` , `courses_id` , `department_id` , `term`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_enrollment_users1_idx` ON `csm`.`watchlist` (`users_id` ASC);

CREATE INDEX `fk_watchlist_terms1_idx` ON `csm`.`watchlist` (`term` ASC);

CREATE INDEX `fk_watchlist_schedule1_idx` ON `csm`.`watchlist` (`schedule_id` ASC, `schedule_courses_id` ASC, `schedule_department_id` ASC, `schedule_term` ASC);

CREATE  TABLE IF NOT EXISTS users (
  id INT NOT NULL auto_increment ,
  username VARCHAR(45) ,
  password VARCHAR(45) ,
  first_name VARCHAR(45) NULL ,
  last_name VARCHAR(45) NULL ,
  email VARCHAR(45) NULL,
  cin  INT NOT NULL,
  PRIMARY KEY (id) );


-- -----------------------------------------------------
-- Insert into users
-- -----------------------------------------------------

insert into users (username,password,first_name,last_name,email,cin)
        values('gpetros','student123','Gayaneh','Petrossian','gpetros@calstatela.edu',999666333);
insert into users (username,password,first_name,last_name,email,cin)
       values ('ebayazian','student123','Elnaz','Bayazian','ebayazian@calsutatela.edu',123456789);
insert into users (username,password,first_name,last_name,email,cin)
       values ('eliao','student123','Eric','Liao','eliao@calstatela.edu',888444222);
insert into users (username,password,first_name,last_name,email,cin)
       values ('ssaeedi','student123','Saman','Saeedi','ssaeedi@calstatela.edu',555666777);

-- -----------------------------------------------------
-- Insert into Term
-- -----------------------------------------------------
insert into terms (description ,status )
       values('summer 2014',true);
insert into terms (description ,status )
       values('fall 2014',true);

-- -----------------------------------------------------
-- Insert into department
-- -----------------------------------------------------
insert into department(name,phone,department_abbr)
       values('Computer Science','323-343-6690','CS');


-- -----------------------------------------------------
-- Insert into Courses
-- -----------------------------------------------------
insert into courses (subject,number,credit,units,departments_id)
      values('cs','520',true,4,1);
insert into courses (subject,number,credit,units,departments_id)
      values('cs','537',true,4,1);
insert into courses (subject,number,credit,units,departments_id)
      values('cs','512',true,4,1);


-- -----------------------------------------------------
-- Insert into Schedule
-- -----------------------------------------------------
insert into schedule (term,class_number,section,room,start_date,end_date,status,courses_id,capacity,department_id)
			values(1,12320,25,'E&T A-309','2014-06-23 13:10:00','2014-07-30 15:30:00',true,1,30,1);

insert into schedule (term,class_number,section,room,start_date,end_date,status,courses_id,capacity,department_id)
			values(1,12215,15,'E&T A-315','2014-06-24 10:00:00','2014-07-31 12:30:00',true,2,30,1);

insert into schedule (term,class_number,section,room,start_date,end_date,status,courses_id,capacity,department_id)
			values(1,12512,20,'E&T A-323','2014-06-28 13:10:00' ,'2014-08-02 17:30:00',true,3,30,1);

-- -----------------------------------------------------
-- Insert into Faculty
-- -----------------------------------------------------

insert into faculty (`title`,`first_name` ,`last_name`, `office_room_number` ,`office_phone`,
					`email` ,  `web_url` ,  `departments_id` )
			values ('Dr.','Russel J.','Abbott','E&T A-325',' (323) 343-6695','rabbot@calstatela.edu','http://cs.calstatela.edu/abbott',1);

insert into faculty (`title`,`first_name` ,`last_name`, `office_room_number` ,`office_phone`,
					`email` ,  `web_url` ,  `departments_id` )
			values ('Dr.','Behzad','Parviz','E&T A-312',' (323) 343-6696','bparviz@calstatela.edu','http://www.calstatela.edu/faculty/bparviz',1);
insert into faculty (`title`,`first_name` ,`last_name`, `office_room_number` ,`office_phone`,
					`email` ,  `web_url` ,  `departments_id` )
			values ('Dr.','Raj S.','Pamula','E&T A-324',' (323) 343-6693','rpamula@calstatela.edu','http://www.calstatela.edu/faculty/rpamula',1);

insert into faculty (`title`,`first_name` ,`last_name`, `office_room_number` ,`office_phone`,
					`email` ,  `web_url` ,  `departments_id` )
			values ('Dr.','Chengyu','Sun',' E&T A-317',' (323) 343-6697','csun@calstatela.edu','e: http://sun.calstatela.edu/~cysun/www/index.html',1);
			
-- -----------------------------------------------------
-- Insert into Faculty
-- -----------------------------------------------------
start transaction;
insert into enrollment
(term,status,users_id,schedule_id)
values(1,'CA',1,1);

update schedule set capacity=capacity-1 ,
					status= (case when capacity<=1 then false else true end )  where id=1;
commit;
