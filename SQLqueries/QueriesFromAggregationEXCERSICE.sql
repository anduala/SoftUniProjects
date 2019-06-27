USE `gringotts`;



SELECT deposit_group, SUM(deposit_amount) AS "total_sum"
FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY total_sum;

SELECT deposit_group, SUM(deposit_amount) AS "total_sum"
FROM wizzard_deposits
WHERE magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group
ORDER BY deposit_group;


SELECT deposit_group, SUM(deposit_amount) AS "total_sum"
FROM wizzard_deposits
WHERE magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group
HAVING `total_sum` < 150000
ORDER BY total_sum DESC;


SELECT 
CASE
WHEN `age` <=10 THEN '[0-10]'
WHEN `age` <=20 THEN '[11-20]'
WHEN `age` <=30 THEN '[21-30]'
WHEN `age` <=40 THEN '[31-40]'
WHEN `age` <=50 THEN '[41-50]'
WHEN `age` <=60 THEN '[51-60]'
ELSE '[61+]'
END
AS 'age_group',
COUNT(age) 
AS 'wizzard_count'
FROM `wizzard_deposits`
GROUP BY `age_group`
ORDER BY `age_group` ASC;


SELECT `deposit_group`, `is_deposit_expired`, AVG(`deposit_interest`) AS 'average_interest'
FROM `wizzard_deposits`
WHERE `deposit_start_date` > '1985/01/01'
GROUP BY `is_deposit_expired`, `deposit_group`
ORDER BY `deposit_group` DESC,`is_deposit_expired`;


SELECT SUM(diff.next) AS 'sum_difference'
FROM(
	SELECT`deposit_amount` -
			(SELECT`deposit_amount`
            FROM `wizzard_deposits`
            WHERE `id` = wd.id + 1) AS 'next'
	FROM `wizzard_deposits` AS wd) AS diff;


SELECT department, SUM(sales) AS "Total sales"
FROM order_details
GROUP BY department;

SELECT SUM(id) as 'count'FROM wizzard_deposits
GROUP BY id
ORDER BY id DESC
LIMIT 1;

SELECT MAX(magic_wand_size) AS 'longest_magic_wand' FROM wizzard_deposits;

SELECT deposit_group, MAX(magic_wand_size) AS 'longest_magic_wand' FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY `longest_magic_wand`ASC ,deposit_group ASC;

SELECT deposit_group FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY AVG(magic_wand_size) ASC
LIMIT 1;



SELECT deposit_group,magic_wand_creator, MIN(deposit_charge) AS 'min_deposit_charge' FROM wizzard_deposits
GROUP BY deposit_group,magic_wand_creator
ORDER BY magic_wand_creator ASC,deposit_group ASC;

SELECT DISTINCT LEFT (first_name , 1) AS 'first_latter' FROM wizzard_deposits
WHERE deposit_group LIKE 'Troll Chest'
ORDER BY first_latter;

USE soft_uni;



SELECT department_id, MIN(salary) AS 'minimum_salary'FROM employees
WHERE department_id =2 OR department_id =5 OR department_id =7 AND hire_date > '2/01/01'
GROUP BY department_id
ORDER BY department_id;


SELECT department_id,
		CASE
        WHEN department_id = 1 THEN AVG(salary) + 5000
        ELSE AVG(salary)
        END as 'avg_salary' FROM employees
WHERE salary > 30000 AND manager_id !=42
GROUP BY department_id
ORDER BY department_id;


SELECT department_id,MAX(salary) AS 'max_salary' FROM employees
GROUP BY department_id
HAVING `max_salary` > 70000 OR `max_salary` < 30000
ORDER BY department_id;

SELECT COUNT(salary) FROM employees
WHERE manager_id IS NULL;

SELECT `department_id`,
(SELECT DISTINCT e2.salary FROM employees AS e2
WHERE e2.department_id = e1.department_id
ORDER BY e2.salary DESC
LIMIT 2 , 1) AS `third_highest_salry`
FROM employees AS e1
GROUP BY department_id
HAVING third_highest_salry IS NOT NULL;

SELECT `first_name`,`last_name`,`department_id` FROM employees
WHERE salary > AVG(salary)
ORDER BY department_id,salary
LIMIT 10;


SELECT
e_out.first_name,
e_out.last_name,
e_out.department_id
FROM employees AS e_out
WHERE (
SELECT avg(e_in.salary) 
FROM employees AS e_in
GROUP BY e_in.department_id
HAVING e_out.department_id = e_in.department_id
) < e_out.salary
ORDER BY e_out.department_id,e_out.employee_id
LIMIT 10;



SELECT department_id,salary AS 'avg_salary' FROM employees
ORDER BY department_id









