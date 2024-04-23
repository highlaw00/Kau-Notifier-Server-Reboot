drop table if exists member;
drop table if exists source;
drop table if exists subscription;

CREATE TABLE `member` (
                          `creation_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                          `member_id` bigint NOT NULL AUTO_INCREMENT,
                          `update_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          `email` varchar(255) NOT NULL,
                          `name` varchar(255) NOT NULL,
                          PRIMARY KEY (`member_id`),
                          UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=372 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `source` (
                          `creation_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                          `source_id` bigint NOT NULL AUTO_INCREMENT,
                          `update_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          `description` varchar(255) DEFAULT NULL,
                          `name` varchar(255) DEFAULT NULL,
                          `url` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `subscription` (
                                `creation_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                `member_id` bigint NOT NULL,
                                `source_id` bigint NOT NULL,
                                `subscription_id` bigint NOT NULL AUTO_INCREMENT,
                                PRIMARY KEY (`subscription_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1329 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE subscription
ADD CONSTRAINT subscription_member_fk
FOREIGN KEY (member_id)
REFERENCES member (member_id) ON DELETE CASCADE;

ALTER TABLE subscription
ADD CONSTRAINT subscription_source_fk
FOREIGN KEY (source_id)
REFERENCES source (source_id);