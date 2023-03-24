-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ltw_local
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ltw_local
-- -----------------------------------------------------
DROP database IF EXISTS `ltw_local`;
CREATE database `ltw_local` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ltw_local` ;

-- -----------------------------------------------------
-- Table `ssafyweb`.`article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ltw_local`.`article` ;

CREATE TABLE IF NOT EXISTS `ltw_local`.`article` (
                                                     `id` INT NOT NULL AUTO_INCREMENT,
                                                     `subject` VARCHAR(100) NULL DEFAULT NULL,
    `content` VARCHAR(2000) NULL DEFAULT NULL,
    `hit` INT NULL DEFAULT 0,
    `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
-- 	`modify_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `member_id` VARCHAR(16) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    -- INDEX `board_to_members_user_id_fk` (`user_id` ASC) VISIBLE,
    -- CONSTRAINT `board_to_members_user_id_fk`
    -- FOREIGN KEY (`member_id`)
    -- REFERENCES `ssafyweb`.`members` (`user_id`)
    )
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into `article` (subject, content, hit) value ("testSubject", "testContent", 0);

select * from article;