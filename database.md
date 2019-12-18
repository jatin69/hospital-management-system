# Database structure

Three tables are used.

```
** Patients Table **

p_id
name
sex
DOB
address
contact no
```

```
** Doctor Table ** 

d_id
name
Specialization
contact_no
Consulting_fee
```

```
** Treatment Table **

d_id
p_id
presciptions
```

## Commands to generate database

```
CREATE TABLE `hospital`.`admin` (
  `admin_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Name` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  `contact_no` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY (`admin_id`)  COMMENT '');



CREATE TABLE `hospital`.`patient` (
  `patient_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `sex` VARCHAR(45) NOT NULL COMMENT '',
  `DOB` DATE NOT NULL COMMENT '',
  `address` VARCHAR(45) NOT NULL COMMENT '',
  `contact_no` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY (`patient_id`)  COMMENT '');


CREATE TABLE `hospital`.`doctor` (
  `doctor_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `specialization` VARCHAR(100) NOT NULL COMMENT '',
  `contact_no` VARCHAR(15) NOT NULL COMMENT '',
  `consulting_fee` INT NOT NULL COMMENT '',
  PRIMARY KEY (`doctor_id`)  COMMENT '');


CREATE TABLE `hospital`.`treatment` (
  `doctor_id` INT NOT NULL COMMENT '',
  `patient_id` INT NOT NULL COMMENT '',
  `prescription` VARCHAR(400) NOT NULL COMMENT '');

```
