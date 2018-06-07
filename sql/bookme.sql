-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bookme
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bookme
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bookme` DEFAULT CHARACTER SET utf8 ;
USE `bookme` ;

-- -----------------------------------------------------
-- Table `bookme`.`Publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookme`.`Publisher` (
  `publisher_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`publisher_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookme`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookme`.`Category` (
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookme`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookme`.`Book` (
  `ISBN` INT UNSIGNED NOT NULL,
  `title` VARCHAR(300) NOT NULL,
  `publisher` VARCHAR(100) NOT NULL,
  `publication_year` CHAR(4),
  `category` VARCHAR(45) NOT NULL,
  `price` DOUBLE UNSIGNED NULL DEFAULT 10,
  `threshold` INT UNSIGNED NULL DEFAULT 0,
  `copies` INT UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`ISBN`),
  INDEX `TITLE_INDEX` (`title` ASC),
  INDEX `CATEGORY_INDEX` (`category` ASC),
  INDEX `PUBLISHER_INDEX` (`publisher` ASC),
  CONSTRAINT `fk_publisher`
    FOREIGN KEY (`publisher`)
    REFERENCES `bookme`.`Publisher` (`publisher_name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_category`
    FOREIGN KEY (`category`)
    REFERENCES `bookme`.`Category` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'This Table will represent the books available in the store';


-- -----------------------------------------------------
-- Table `bookme`.`Author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookme`.`Author` (
  `author_name` VARCHAR(100) NOT NULL,
  `ISBN` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ISBN`, `author_name`),
  CONSTRAINT `fk_book_id_author`
    FOREIGN KEY (`ISBN`)
    REFERENCES `bookme`.`Book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'This Table will contain all authors with their books';


-- -----------------------------------------------------
-- Table `bookme`.`PublisherAddress`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookme`.`PublisherAddress` (
  `publisher_name` VARCHAR(100) NOT NULL,
  `publisher_address` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`publisher_name`, `publisher_address`),
  CONSTRAINT `fk_publisher_name_address`
    FOREIGN KEY (`publisher_name`)
    REFERENCES `bookme`.`Publisher` (`publisher_name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookme`.`PublisherPhone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookme`.`PublisherPhone` (
  `publisher_name` VARCHAR(100) NOT NULL,
  `publisher_phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`publisher_phone`, `publisher_name`),
  CONSTRAINT `fk_publisher_name_phone`
    FOREIGN KEY (`publisher_name`)
    REFERENCES `bookme`.`Publisher` (`publisher_name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookme`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookme`.`Order` (
  `order_id` VARCHAR(36) NOT NULL,
  `ISBN` INT UNSIGNED NOT NULL,
  `quantity` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`, `ISBN`),
  INDEX `fk_book_id_idx` (`ISBN` ASC),
  CONSTRAINT `fk_book_id_order`
    FOREIGN KEY (`ISBN`)
    REFERENCES `bookme`.`Book` (`ISBN`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookme`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookme`.`User` (
  `user_id` VARCHAR(36) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` blob NOT NULL,
  `salt` blob NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `shipping_address` VARCHAR(100) NOT NULL,
  `is_manager` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookme`.`Sale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookme`.`Sale` (
  `sale_id` VARCHAR(36) NOT NULL,
  `user_id` VARCHAR(36) NULL,
  `ISBN` INT UNSIGNED NULL,
  `sale_date` DATE NULL,
  `copies` INT UNSIGNED NULL,
  PRIMARY KEY (`sale_id`),
  INDEX `fk_user_id_user_idx` (`user_id` ASC),
  INDEX `fk_book_id_book_idx` (`ISBN` ASC),
  CONSTRAINT `fk_user_id_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `bookme`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_id_book`
    FOREIGN KEY (`ISBN`)
    REFERENCES `bookme`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `bookme`;

DELIMITER $$
USE `bookme`$$
CREATE DEFINER = CURRENT_USER TRIGGER `bookme`.`ModifyBook` BEFORE UPDATE ON `Book` FOR EACH ROW
BEGIN

if new.copies < 0 then
	signal sqlstate '45000';
end if;

END$$

USE `bookme`$$
CREATE DEFINER = CURRENT_USER TRIGGER `bookme`.`PlaceOrder` AFTER UPDATE ON `Book` FOR EACH ROW
BEGIN

declare to_order INT;

set to_order = new.copies - new.threshold;

if to_order < 0 then
	insert into bookme.order values (UUID(), new.ISBN, to_order);
 end if;

END$$

USE `bookme`$$
CREATE DEFINER = CURRENT_USER TRIGGER `bookme`.`ConfirmOrder` BEFORE DELETE ON `Order` FOR EACH ROW
BEGIN

update book set copies = copies + old.quantity where ISBN = old.ISBN;

END$$

USE `bookme`$$
CREATE DEFINER = CURRENT_USER TRIGGER `bookme`.`Sale_AFTER_INSERT` AFTER INSERT ON `Sale` FOR EACH ROW
BEGIN
	update `bookme`.Book set copies = copies - new.copies where ISBN = new.ISBN;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
