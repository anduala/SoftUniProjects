SELECT `job_title`,
count(*) FROM soft_uni.employees
group by `job_title`;


SELECT `department_id`,COUNT(*)
FROM `employees`
group by `department_id`
order by `department_id`;

SELECT `department_id`,ROUND(AVG(`salary`),2)
FROM `employees`
group by `department_id`
order by `department_id`;

SELECT `department_id`,
ROUND(MIN(`salary`),2) AS 'MIN'
FROM `employees`
group by `department_id`
HAVING `MIN` > 800
order by `department_id`;

SELECT count(*)
FROM `products`
WHERE `category_id` = 2 AND `price` > 8;

SELECT `category_id`,
ROUND(AVG(`price`),2)AS 'Average Price',
ROUND(MIN(`price`),2)AS 'Cheapest Product',
ROUND(MAX(`price`),2)AS 'Most Expensive'
FROM `products`
GROUP BY `category_id`
ORDER BY `category_id`;

SELECT user_name, ip_address FROM users
WHERE ip_address LIKE  '___.1%.%.___'
ORDER BY user_name;

 