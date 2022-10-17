-- MySQL Script generated by MySQL Workbench
-- Sun Oct 16 14:28:05 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema army_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema army_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `army_db` DEFAULT CHARACTER SET utf8 ;
USE `army_db` ;

-- -----------------------------------------------------
-- Table `army_db`.`armies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`armies` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `number` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`small_arms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`small_arms` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `number` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`generals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`generals` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `army_id` BIGINT UNSIGNED NOT NULL,
  `small_arm_id` BIGINT UNSIGNED NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `military_badge` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Generals_armies_idx` (`army_id` ASC) VISIBLE,
  UNIQUE INDEX `military_badge_UNIQUE` (`military_badge` ASC) VISIBLE,
  INDEX `fk_Generals_small_arms1_idx` (`small_arm_id` ASC) VISIBLE,
  CONSTRAINT `fk_Generals_armies`
    FOREIGN KEY (`army_id`)
    REFERENCES `army_db`.`armies` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Generals_small_arms1`
    FOREIGN KEY (`small_arm_id`)
    REFERENCES `army_db`.`small_arms` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`officers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`officers` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `general_id` BIGINT UNSIGNED NOT NULL,
  `small_arm_id` BIGINT UNSIGNED NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `military_badge` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `military_badge_UNIQUE` (`military_badge` ASC) VISIBLE,
  INDEX `fk_Officers_small_arms1_idx` (`small_arm_id` ASC) VISIBLE,
  INDEX `fk_Officers_Generals1_idx` (`general_id` ASC) VISIBLE,
  CONSTRAINT `fk_Officers_small_arms1`
    FOREIGN KEY (`small_arm_id`)
    REFERENCES `army_db`.`small_arms` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Officers_Generals1`
    FOREIGN KEY (`general_id`)
    REFERENCES `army_db`.`generals` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`heavy_weapons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`heavy_weapons` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `number` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`soldiers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`soldiers` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `small_arm_id` BIGINT UNSIGNED NOT NULL,
  `heavy_weapon_id` BIGINT UNSIGNED NULL,
  `officer_id` BIGINT UNSIGNED NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `demobilization` DATE NOT NULL,
  `military_badge` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `military_badge_UNIQUE` (`military_badge` ASC) VISIBLE,
  INDEX `fk_Soldiers_small_arms1_idx` (`small_arm_id` ASC) VISIBLE,
  INDEX `fk_Soldiers_heavy_weapon1_idx` (`heavy_weapon_id` ASC) VISIBLE,
  INDEX `fk_soldiers_officers1_idx` (`officer_id` ASC) VISIBLE,
  CONSTRAINT `fk_Soldiers_small_arms1`
    FOREIGN KEY (`small_arm_id`)
    REFERENCES `army_db`.`small_arms` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Soldiers_heavy_weapon1`
    FOREIGN KEY (`heavy_weapon_id`)
    REFERENCES `army_db`.`heavy_weapons` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_soldiers_officers1`
    FOREIGN KEY (`officer_id`)
    REFERENCES `army_db`.`officers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`tanks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`tanks` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `officer_id` BIGINT UNSIGNED NOT NULL,
  `number` INT NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  INDEX `fk_tanks_Officers1_idx` (`officer_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_tanks_Officers1`
    FOREIGN KEY (`officer_id`)
    REFERENCES `army_db`.`officers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`planes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`planes` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `officer_id` BIGINT UNSIGNED NOT NULL,
  `number` INT NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  INDEX `fk_planes_Officers1_idx` (`officer_id` ASC) VISIBLE,
  CONSTRAINT `fk_planes_Officers1`
    FOREIGN KEY (`officer_id`)
    REFERENCES `army_db`.`officers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`mobile_artilleries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`mobile_artilleries` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `officer_id` BIGINT UNSIGNED NOT NULL,
  `number` INT NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  INDEX `fk_mobile_artilleries_Officers1_idx` (`officer_id` ASC) VISIBLE,
  CONSTRAINT `fk_mobile_artilleries_Officers1`
    FOREIGN KEY (`officer_id`)
    REFERENCES `army_db`.`officers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`infantry_vehicles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`infantry_vehicles` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `officer_id` BIGINT UNSIGNED NOT NULL,
  `number` INT NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  INDEX `fk_infantry_vehicles_Officers1_idx` (`officer_id` ASC) VISIBLE,
  CONSTRAINT `fk_infantry_vehicles_Officers1`
    FOREIGN KEY (`officer_id`)
    REFERENCES `army_db`.`officers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`heavy_artilleries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`heavy_artilleries` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `officer_id` BIGINT UNSIGNED NOT NULL,
  `number` INT NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  INDEX `fk_heavy_artilleries_Officers1_idx` (`officer_id` ASC) VISIBLE,
  CONSTRAINT `fk_heavy_artilleries_Officers1`
    FOREIGN KEY (`officer_id`)
    REFERENCES `army_db`.`officers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`rewards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`rewards` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `army_db`.`soldier_rewards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `army_db`.`soldier_rewards` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `soldier_id` BIGINT UNSIGNED NOT NULL,
  `rewards_id` BIGINT UNSIGNED NOT NULL,
  INDEX `fk_soldiers_has_rewards_rewards1_idx` (`rewards_id` ASC) VISIBLE,
  INDEX `fk_soldiers_has_rewards_soldiers1_idx` (`soldier_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_soldiers_has_rewards_soldiers1`
    FOREIGN KEY (`soldier_id`)
    REFERENCES `army_db`.`soldiers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_soldiers_has_rewards_rewards1`
    FOREIGN KEY (`rewards_id`)
    REFERENCES `army_db`.`rewards` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
