-- MySQL Script generated by MySQL Workbench
-- Fri Oct  6 16:58:46 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Articles
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Articles
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Articles` DEFAULT CHARACTER SET utf8 ;
USE `Articles` ;

-- -----------------------------------------------------
-- Table `Articles`.`Articles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Articles`.`Articles` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `author` VARCHAR(45) NULL,
  `contents` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `Articles`.`Articles`
-- -----------------------------------------------------
START TRANSACTION;
USE `Articles`;
INSERT INTO `Articles`.`Articles` (`id`, `title`, `author`, `contents`) VALUES (1, 'Title', 'Author', 'Content');

COMMIT;
