CREATE SCHEMA `dao3` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `dao3`.`manufacturers` (
`manufacturer_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`country` VARCHAR(255) NOT NULL,
`deleted` TINYINT NULL DEFAULT 0,
PRIMARY KEY (`manufacturer_id`));

CREATE TABLE `dao3`.`drivers` (
`driver_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`licence_number` VARCHAR(255) NOT NULL,
`login` VARCHAR(255) NOT NULL,
`password` VARCHAR(255) NOT NULL,
`deleted` TINYINT NULL DEFAULT 0,
PRIMARY KEY (`driver_id`));

CREATE TABLE `dao3`.`cars` (
`car_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
`model` VARCHAR(255) NOT NULL,
`manufacturer_id` BIGINT(11) NOT NULL,
`deleted` TINYINT NOT NULL DEFAULT 0,
PRIMARY KEY (`car_id`),
INDEX `cars_manufacturers_fk_idx` (`manufacturer_id` ASC) VISIBLE,
CONSTRAINT `cars_manufacturers_fk`
FOREIGN KEY (`manufacturer_id`)
REFERENCES `dao3`.`manufacturers` (`manufacturer_id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION);

CREATE TABLE `dao3`.`cars_drivers` (
`car_id` BIGINT(11) NOT NULL,
`diver_id` BIGINT(11) NOT NULL,
INDEX `drivers_cars_fk_idx` (`diver_id` ASC) VISIBLE,
INDEX `cars_drivers_fk_idx` (`car_id` ASC) VISIBLE,
CONSTRAINT `drivers_cars_fk`
FOREIGN KEY (`diver_id`)
REFERENCES `dao3`.`drivers` (`driver_id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
CONSTRAINT `cars_drivers_fk`
FOREIGN KEY (`car_id`)
REFERENCES `dao3`.`cars` (`car_id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION);
