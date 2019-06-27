SELECT e.employee_id, e.first_name, e2.employee_id,e2.first_name FROM employees AS e
JOIN employees AS e2
ON e2.employee_id = e.manager_id
WHERE e.manager_id IN (3,7)
ORDER BY e.first_name;



SELECT e.employee_id,
CONCAT(e.first_name,' ',e.last_name) AS `employee_name`,
CONCAT(e2.first_name,' ',e2.last_name) AS `manager_name`,
d.name AS 'deparment_name'
FROM employees AS e
JOIN employees AS e2
ON e2.employee_id = e.manager_id
JOIN departments AS d
ON e.department_id = d.department_id
ORDER BY e.employee_id
LIMIT 5;



SELECT MIN(min_avg_salary) AS 'min_average_salary'
FROM(
	SELECT AVG(salary) AS min_avg_salary
    FROM employees
    GROUP BY department_id
) AS min_salaries;

USE geography;



SELECT mc.country_code, m.mountain_range,
p.peak_name, p.elevation
FROM mountains_countries AS mc
JOIN mountains AS m
ON mc.mountain_id = m.id
JOIN peaks AS p
ON m.id = p.mountain_id
WHERE mc.country_code = 'BG' AND p.elevation > 2835
ORDER BY p.elevation DESC;



SELECT c.country_name,r.river_name
FROM rivers AS r
RIGHT JOIN countries_rivers AS cr
ON r.id = cr.river_id
RIGHT JOIN countries AS c
ON cr.country_code = c.country_code
WHERE c.continent_code = 'AF'
ORDER BY c.country_name
LIMIT 5;



SELECT e.first_name,e.last_name,t.name,a.address_text FROM employees AS e
JOIN addresses AS a
ON e.address_id = a.address_id
JOIN towns AS t
ON t.town_id = a.town_id
ORDER BY e.first_name,e.last_name
LIMIT 5;



SELECT e.employee_id,e.first_name FROM employees AS e
JOIN employees_projects AS ep
ON e.employee_id != ep.employee_id 
GROUP BY e.employee_id
ORDER BY e.employee_id DESC
LIMIT 3;



SELECT e.employee_id , e.first_name FROM employees AS e
LEFT JOIN employees_projects as e_p  
ON e.employee_id = e_p.employee_id
WHERE e_p.employee_id IS NULL
ORDER BY e.employee_id DESC LIMIT 3;


SELECT e.first_name,e.last_name,e.hire_date,d.name AS 'dept_name' FROM employees AS e
INNER JOIN departments AS d
ON e.department_id = d.department_id
WHERE d.name IN ('Sales', 'Finance') AND DATE(e.hire_date) > '1999-01-01'
ORDER BY e.hire_date;


SELECT e.employee_id,e.first_name
,CASE
WHEN year (p.start_date) >= '2005' THEN NULL
ELSE p.`name`
END AS 'project_name'
 FROM employees AS e
JOIN employees_projects AS ep
ON e.employee_id = ep.employee_id
RIGHT JOIN projects AS p
ON ep.project_id = p.project_id
WHERE e.employee_id = 24
ORDER BY project_name
LIMIT 5;


USE geography;



SELECT e.employee_id,e.first_name,p.name AS 'project_name' FROM employees AS e
JOIN employees_projects AS ep
ON e.employee_id = ep.employee_id
JOIN projects AS p
ON ep.project_id = p.project_id
WHERE DATE(p.start_date) > '2002-08-13'AND p.end_date IS NULL
ORDER BY e.first_name,project_name
LIMIT 5;



SELECT e.employee_id,e.first_name,e.last_name,d.name AS "department_name" FROM employees AS e
JOIN departments AS d
ON e.department_id = d.department_id
WHERE d.name ='Sales'
ORDER BY e.employee_id DESC;



SELECT e.employee_id,e.first_name,e.salary,d.name AS "department_name" FROM employees AS e
JOIN departments AS d
ON e.department_id = d.department_id
WHERE e.salary > 15000
ORDER BY d.department_id DESC
LIMIT 5;



SELECT c.country_name, MAX(p.elevation) AS 'highest_peak_elevation',
MAX(r.length) AS 'longest_river_length'
FROM countries AS c
LEFT JOIN mountains_countries AS mc
ON mc.country_code = c.country_code
LEFT JOIN peaks AS p
ON mc.mountain_id = p.mountain_id
LEFT JOIN countries_rivers AS cr
ON cr.country_code = c.country_code
LEFT JOIN rivers AS r
ON cr.river_id = r.id
GROUP BY c.country_name
ORDER BY highest_peak_elevation DESC,longest_river_length DESC, c.country_name
LIMIT 5;



SELECT c.country_name,r.river_name FROM countries AS c
LEFT JOIN countries_rivers AS cr
ON c.country_code = cr.country_code
LEFT JOIN rivers AS r
ON cr.river_id = r.id
WHERE c.continent_code = 'AF'
ORDER BY c.country_name
LIMIT 5;



SELECT c.country_code,COUNT(m.mountain_range) AS 'mountain_range' FROM countries AS c
JOIN mountains_countries AS mc
ON c.country_code = mc.country_code
JOIN mountains AS m
ON mc.mountain_id = m.id
WHERE c.country_code IN ('US','BG','RU')
GROUP BY c.country_code
ORDER BY mountain_range DESC;



Select
	Count(c.country_code) - Count(mc.country_code) As country_count
 From countries AS c
   Left Outer Join mountains_countries AS mc 
   On mc.country_code IS NOT NULL;
   
   

SELECT c.continent_code,c.currency_code,count(currency_code) AS currency_usage
FROM countries AS c
GROUP BY continent_code
ORDER BY continent_code,currency_code;



SELECT (COUNT(*) - COUNT(mc.mountain_id)) AS country_count
FROM countries AS c
LEFT JOIN mountains_countries AS mc
ON c.country_code = mc.country_code;



SELECT (COUNT(*) - COUNT(mc.mountain_id)) as country_count
FROM countries as c
LEFT JOIN mountains_countries as mc
ON c.country_code = mc.country_code;



SELECT COUNT(c.country_name) FROM mountains_countries AS mc
RIGHT JOIN countries AS c
ON mc.country_code = c.country_code
WHERE mc.mountain_id iS NULL;



SELECT d1.continent_code, d1.currency_code, d1.currency_usage FROM
    (SELECT `c`.`continent_code`, `c`.`currency_code`,
    COUNT(`c`.`currency_code`) AS `currency_usage` FROM countries as c
    GROUP BY c.currency_code, c.continent_code HAVING currency_usage > 1) as d1
LEFT JOIN
    (SELECT `c`.`continent_code`,`c`.`currency_code`,
    COUNT(`c`.`currency_code`) AS `currency_usage` FROM countries as c
     GROUP BY c.currency_code, c.continent_code HAVING currency_usage > 1) as d2
ON d1.continent_code = d2.continent_code AND d2.currency_usage > d1.currency_usage
WHERE d2.currency_usage IS NULL
ORDER BY d1.continent_code, d1.currency_code;





CREATE TABLE groups_currency
SELECT
	continent_code,
	currency_code,
	count(currency_code) AS currency_usage
FROM countries
GROUP BY continent_code, currency_code
HAVING currency_usage > 1;

SELECT
	allgr.continent_code,
	allgr.currency_code,
	allgr.currency_usage
FROM
	groups_currency AS allgr,
	(SELECT
		continent_code,
		currency_code,
		max(currency_usage) AS currency_usage
	FROM 
		groups_currency
	GROUP BY continent_code
    ) AS maxims
WHERE allgr.continent_code = maxims.continent_code AND allgr.currency_usage = maxims.currency_usage
ORDER BY
	continent_code,
    currency_code;

