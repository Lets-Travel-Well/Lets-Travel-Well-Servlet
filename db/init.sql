DROP database IF EXISTS `ltw_local`;
CREATE database `ltw_local` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ltw_local` ;

DROP TABLE IF EXISTS `ltw_local`.`Member` ;

CREATE TABLE `ltw_local`.`Member` (
                                      `id`    BIGINT    NOT NULL AUTO_INCREMENT,
                                      `login_id`    VARCHAR(20)    NULL DEFAULT NULL,
                                      `login_pw`    VARCHAR(20)    NULL DEFAULT NULL,
                                      `username`    VARCHAR(20)    NULL DEFAULT NULL,
                                      `email`    VARCHAR(20)    NULL DEFAULT NULL,
                                      `phone`    VARCHAR(11)    NULL DEFAULT NULL,
                                      `created_date`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      `modified_date`    TIMESTAMP    NULL DEFAULT NULL,
                                      PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `ltw_local`.`Article` ;

CREATE TABLE `ltw_local`.`Article` (
                                       `id`    BIGINT    NOT NULL AUTO_INCREMENT,
                                       `created_date`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       `modified_date`    TIMESTAMP    NULL DEFAULT NULL,
                                       `subject`    VARCHAR(100)    NULL DEFAULT NULL,
                                       `content`    VARCHAR(2000)    NULL DEFAULT NULL,
                                       `hit`    INT NULL DEFAULT 0,
                                       `member_id`    BIGINT    NOT NULL,
                                       PRIMARY KEY (`id`),
                                       FOREIGN KEY (member_id) REFERENCES Member(id)
);

DROP TABLE IF EXISTS `ltw_local`.`My_Attraction`;

CREATE TABLE `ltw_local`.`My_Attraction`
(
    `id`                    BIGINT    NOT NULL AUTO_INCREMENT,
    `created_date`          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `modified_date`         TIMESTAMP NULL DEFAULT NULL,
    `member_id`             BIGINT    NOT NULL,
    `attraction_id`    VARCHAR(2000) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (member_id) REFERENCES Member (id)
-- ERROR : 1071
--     FOREIGN KEY (attraction_id) REFERENCES attraction_info (content_id)
);

INSERT INTO `ltw_local`.`member` (`login_id`, `login_pw`, `username`, `email`, `phone`) VALUES
                                                                                            ('qwe', 'qwe', 'kcc', 'test1@tes.com', '01012341234'),
                                                                                            ('ssafy', '1234','김싸피', 'ssafy@ssafy.com', '01012341234'),
                                                                                            ('admin', '1234','관리자', 'admin@google.com', '01000000000');