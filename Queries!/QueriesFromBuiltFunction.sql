USE soft_uni;

SELECT e.`first_name`, e.`last_name` FROM employees AS e
WHERE  SUBSTRING(`first_name`,1,2)='Sa'
ORDER BY e.employee_id;

SELECT `first_name` FROM `employees`
WHERE department_id in (3,10) AND YEAR(`hire_date`) >= 1995
AND YEAR(`hire_date`) <=2005
ORDER BY employee_id;

SELECT `name` FROM `towns` 
WHERE length(`name`)= 5 OR length(`name`)= 6
ORDER BY `name`;

SELECT `town_id`,`name` FROM `towns` 
WHERE `name` NOT LIKE 'R%' AND `name` NOT LIKE 'B%'
AND `name` NOT LIKE 'D%' 
ORDER BY `town_id`;

SELECT `town_id`,`name` FROM `towns` 
WHERE `name` LIKE 'E%' OR `name` LIKE 'B%'
OR `name` LIKE 'M%' OR `name` LIKE 'K%' 
ORDER BY `name`;

CREATE VIEW `v_employees_hired_after_2000` AS
SELECT `first_name`,`last_name` FROM employees
WHERE YEAR(`hire_date`)>2000;

SELECT `first_name`,`last_name` FROM employees
WHERE YEAR(`hire_date`)>2000;

SELECT * FROM `v_employees_hired_after_2000`;

USE `geography`;

SELECT p.`peak_name`, 
r.`river_name`,
LOWER(CONCAT(p.`peak_name`,SUBSTRING(r.`river_name`,2)))
AS `mix`

FROM `peaks` AS p, `rivers` AS r

WHERE RIGHT(p.`peak_name`,1) = LEFT (r.`river_name`,1)
ORDER BY `mix` ASC;

SELECT c.`country_name`,c.`iso_code` FROM countries AS c
WHERE c.`country_name` LIKE '%A%A%A%'
ORDER BY c.`iso_code`;


USE diablo;

SELECT name,
DATE_FORMAT(`start`,'%Y-%m-%d') AS `start`
FROM games
WHERE YEAR(`start`) BETWEEN 2011 AND 2012
ORDER BY `start`
LIMIT 50;

SELECT user_name,
SUBSTRING_INDEX(email,'@',-1)
AS `Email Provider`
FROM `users`
ORDER BY `Email Provider` ASC,user_name;



SELECT `name` AS 'game',
CASE
WHEN HOUR(`start`) >= 0 AND HOUR(`start`) <12 THEN 'Morning'
WHEN HOUR(`start`) >= 12 AND HOUR(`start`) <18 THEN 'Afternoon'
WHEN HOUR(`start`) >= 18 AND HOUR(`start`) <24 THEN 'Evening'
END AS `Part of the Day`,
CASE
WHEN `duration` BETWEEN 0 AND 3 THEN 'Extra Short'
WHEN `duration` BETWEEN 3 AND 6 THEN 'Short'
WHEN `duration` BETWEEN 6 AND 10 THEN 'Long'
ELSE 'Extra Long'
END AS `Duration`
FROM `games`;

USE orders;

SELECT `product_name`,`order_date`,
DATE_ADD(`order_date`,INTERVAL 3 DAY) AS `play_due`,
DATE_ADD(`order_date`,INTERVAL 1 MONTH) AS `deliver_due` FROM orders;


USE soft_uni;

SELECT `first_name`,`last_name` FROM `employees`
WHERE `last_name` LIKE "%ei%"
ORDER BY `employee_id`;


SELECT `first_name`,`last_name` FROM `employees`
WHERE `job_title` NOT LIKE "%engineer%"
ORDER BY `employee_id`;


SELECT `first_name`,`last_name` FROM `employees`
WHERE length(`last_name`)= 5;


