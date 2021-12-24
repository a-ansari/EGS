USE egs_bank;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `account`
(
    `id`             bigint(20)   NOT NULL AUTO_INCREMENT,
    `account_number` varchar(100) NOT NULL,
    `account_status` varchar(100) NOT NULL,
    `balance`        bigint(20)   NOT NULL,
    `creation_time`  datetime(6)  NOT NULL,
    `currency`       varchar(10)  NOT NULL,
    `customer_id`    bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `account_number` (`account_number`),
    KEY `FK_ACCOUNT_CUSTOMER` (`customer_id`),
    KEY `account_account_status_IDX` (`account_status`) USING BTREE,
    CONSTRAINT `FK_ACCOUNT_CUSTOMER` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `card`
(
    `id`                bigint(20)   NOT NULL AUTO_INCREMENT,
    `auth_count`        int(11)      NOT NULL DEFAULT '0',
    `card_number`       varchar(100) NOT NULL,
    `card_status`       varchar(100) NOT NULL,
    `cvv2`              int(11)      NOT NULL,
    `expiration_date`   date         NOT NULL,
    `finger_print_hash` varchar(160)          DEFAULT NULL,
    `pin_code_hash`     varchar(160)          DEFAULT NULL,
    `account_id`        bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `card_number` (`card_number`),
    KEY `FK_CARD_ACCOUNT` (`account_id`),
    KEY `card_card_status_IDX` (`card_status`) USING BTREE,
    CONSTRAINT `FK_CARD_ACCOUNT` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `customer`
(
    `id`              bigint(20)   NOT NULL AUTO_INCREMENT,
    `first_name`      varchar(255) NOT NULL,
    `last_name`       varchar(255) NOT NULL,
    `national_id`     varchar(100) DEFAULT NULL,
    `passport_number` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `event_log`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT,
    `card_number` varchar(100) DEFAULT NULL,
    `event_data`  varchar(255) DEFAULT NULL,
    `event_time`  datetime(6)  NOT NULL,
    `event_type`  varchar(20) NOT NULL,
    `event_state`  varchar(20) NOT NULL,
    `session_id`  bigint(20)   DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `session`
(
    `id`            bigint(20)  NOT NULL AUTO_INCREMENT,
    `creation_time` datetime(6) NOT NULL,
    `card_id`       bigint(20)  NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_SESSION_CARD` (`card_id`),
    CONSTRAINT `FK_SESSION_CARD` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
