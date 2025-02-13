-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema class_booking
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema class_booking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `class_booking` DEFAULT CHARACTER SET latin1 ;
USE `class_booking` ;

-- -----------------------------------------------------
-- Table `class_booking`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `class_booking`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `enabled` BIT(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uc_users_email` (`username` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `class_booking`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `class_booking`.`authorities` (
  `username` VARCHAR(100) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  UNIQUE INDEX `authorities_idx_1` (`username` ASC, `authority` ASC),
  CONSTRAINT `authorities_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `class_booking`.`users` (`username`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `class_booking`.`classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `class_booking`.`classes` (
  `id_class` INT NOT NULL AUTO_INCREMENT,
  `class_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_class`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `class_booking`.`instructors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `class_booking`.`instructors` (
  `id_instructor` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_instructor`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `class_booking`.`members`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `class_booking`.`members` (
  `user_id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `class_booking`.`users` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `class_booking`.`schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `class_booking`.`schedule` (
  `id_event` INT NOT NULL AUTO_INCREMENT,
  `id_class` INT NOT NULL,
  `start_time` DATETIME NOT NULL,
  `duration` INT NOT NULL,
  `id_instructor` INT NULL DEFAULT NULL,
  `capacity` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_event`),
  INDEX `id_class_idx` (`id_class` ASC) VISIBLE,
  INDEX `id_instructor_idx` (`id_instructor` ASC) VISIBLE,
  CONSTRAINT `id_class`
    FOREIGN KEY (`id_class`)
    REFERENCES `class_booking`.`classes` (`id_class`),
  CONSTRAINT `id_instructor`
    FOREIGN KEY (`id_instructor`)
    REFERENCES `class_booking`.`instructors` (`id_instructor`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `class_booking`.`user_event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `class_booking`.`user_event` (
  `id_userevent` INT NOT NULL AUTO_INCREMENT,
  `id_user` INT NOT NULL,
  `id_event` INT NOT NULL,
  PRIMARY KEY (`id_userevent`),
  INDEX `id_event_idx` (`id_event` ASC) VISIBLE,
  INDEX `id_user_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `id_event`
    FOREIGN KEY (`id_event`)
    REFERENCES `class_booking`.`schedule` (`id_event`),
  CONSTRAINT `id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `class_booking`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `class_booking`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `class_booking`;
INSERT INTO `class_booking`.`users` (`id`, `username`, `password`, `enabled`) VALUES (DEFAULT, 'admin', '$2a$10$tRlbn8/3Vy0r5OsbOLAq4OkmPALFNMbWDQdrnLU3e6dvBGp3y5jE2', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `class_booking`.`authorities`
-- -----------------------------------------------------
START TRANSACTION;
USE `class_booking`;
INSERT INTO `class_booking`.`authorities` (`username`, `authority`) VALUES ('admin', 'ROLE_ADMIN');

COMMIT;

