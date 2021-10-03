CREATE TABLE `board` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`content` TEXT NOT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=9
;


CREATE TABLE `user` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(50) NOT NULL,
	`password` VARCHAR(100) NOT NULL,
	`enabled` BIT NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `username` (`username`)
) 
COLLATE='utf8mb4_0900_ai_ci'
;
