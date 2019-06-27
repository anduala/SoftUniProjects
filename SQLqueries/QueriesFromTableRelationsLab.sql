SELECT 
r.starting_point AS 'route_starting_point',
r.end_point AS 'route_ending_point',
r.leader_id AS 'leader_id',
CONCAT(c.first_name," ",c.last_name) AS 'leader_name'
FROM campers c
JOIN routes r
ON r.leader_id = c.id;


CREATE TABLE `mountains`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL);
CREATE TABLE `peaks`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL,
`mountain_id` INT,
CONSTRAINT `fk_mountain_id`
FOREIGN KEY(`mountain_id`)
REFERENCES `mountains`(`id`)
ON DELETE CASCADE);



CREATE TABLE `employees`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30),
`last_name` VARCHAR(30),
`project_id` INT(11)
);

CREATE TABLE `projects`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`client_id` INT(11),
`project_lead_id` INT(11)
);

CREATE TABLE `clients`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`client_name` VARCHAR(100),
`project_id` INT(11)
);

ALTER TABLE `clients`
	ADD CONSTRAINT `fk_clients_project` FOREIGN KEY(`project_id`) REFERENCES `projects`(`id`);

ALTER TABLE `employees`
	ADD CONSTRAINT `fk_employees_project` FOREIGN KEY(`project_id`) REFERENCES `projects`(`id`);

ALTER TABLE `projects`
	ADD CONSTRAINT `fk_project_clients` FOREIGN KEY(`client_id`) REFERENCES `clients`(`id`),
	ADD CONSTRAINT `fk_project_employees` FOREIGN KEY(`project_lead_id`) REFERENCES `employees`(`id`);
    
    
    
    

    
CREATE TABLE `employees`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30),
`last_name` VARCHAR(30),
`project_id` INT(11)
);

CREATE TABLE `projects`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`client_id` INT(11),
`project_lead_id` INT(11)
);

CREATE TABLE `clients`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`client_name` VARCHAR(100),
`project_id` INT(11)
);

ALTER TABLE `employees`
	ADD CONSTRAINT `fk_employees_project` FOREIGN KEY(`project_id`) REFERENCES `projects`(`id`);

ALTER TABLE `clients`
	ADD CONSTRAINT `fk_clients_project` FOREIGN KEY(`project_id`) REFERENCES `projects`(`id`);

ALTER TABLE `projects`
	ADD CONSTRAINT `fk_project_clients` FOREIGN KEY(`client_id`) REFERENCES `clients`(`id`),
	ADD CONSTRAINT `fk_project_employees` FOREIGN KEY(`project_lead_id`) REFERENCES `employees`(`id`);
    
    
CREATE TABLE `employees`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30),
`last_name` VARCHAR(30),
`project_id` INT(11)NOT NULL);
CREATE TABLE `clients`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`client_name` VARCHAR(100),
`project_id` INT(11)NOT NULL);
CREATE TABLE `projects`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`client_id` INT(11),
`project_lead_id` INT(11)NOT NULL);
ALTER TABLE `employees`
ADD CONSTRAINT `fk_employees_project`
FOREIGN KEY(`project_id`)
REFERENCES `projects`(`id`);
ALTER TABLE `clients`
ADD CONSTRAINT `fk_clients_project`
FOREIGN KEY(`project_id`)
REFERENCES `projects`(`id`);
ALTER TABLE `projects`
ADD CONSTRAINT `fk_project_clients`
FOREIGN KEY(`client_id`)
REFERENCES `clients`(`id`),
ADD CONSTRAINT `fk_project_employees`
FOREIGN KEY(`project_lead_id`)
REFERENCES `employees`(`id`);