DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value (init_sum DECIMAL(10,4) ,rate DECIMAL(10,4) ,years INT)
RETURNS DECIMAL(10,4) 
BEGIN
	DECLARE future_value DECIMAL(10,4);
    
    SET future_value := init_sum * POW(1+rate,years);
	
    RETURN future_value;
END;$$

DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account(acc_id INT,int_rate DECIMAL(10,4))
BEGIN
	SELECT ac.id, ah.first_name, ah.last_name, ac.balance AS 'current_balance',
			ROUND(ufn_calculate_future_value(ac.balance, int_rate,5),4) AS 'balance_in_5_years'
		FROM account_holders AS ah
        JOIN accounts AS ac
        ON ac.account_holder_id = ah.id
        WHERE ac.id = acc_id
        LIMIT 1;
END;$$

CALL usp_calculate_future_value_for_account(1,0.1);



DELIMITER $$
CREATE FUNCTION ufn_is_word_comprised(letters VARCHAR(50),word VARCHAR(50))
RETURNS INT
BEGIN
	DECLARE indx INT;
    DECLARE symbol VARCHAR(1);
	SET indx :=1;
    
    WHILE indx <= CHAR_LENGTH(word) DO
		SET symbol := SUBSTRING(word,indx,1);
        IF locate(symbol,letters) = 0 THEN
			RETURN 0;
		END IF;
        SET indx := indx +1;
        
	END WHILE;
    RETURN 1;
END;$$

#1
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
SELECT first_name,last_name FROM employees
    WHERE salary > 35000
    ORDER BY first_name,last_name,employee_id;
END;$$


#2
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(parameter DOUBLE)
BEGIN
SELECT first_name,last_name FROM employees
    WHERE salary >= parameter
    ORDER BY first_name,last_name,employee_id;
END;$$


#8
DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	SELECT CONCAT(first_name,' ',last_name) AS 'full_name' FROM account_holders AS ah
    ORDER BY `full_name`, ah.id;
END;$$

#9
DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(parameter DOUBLE)
BEGIN
SELECT ah.first_name,ah.last_name FROM account_holders AS ah
	JOIN accounts AS ac
    ON ah.id = ac.account_holder_id
    WHERE EXISTS (
		SELECT  SUM(ac1.balance) AS sum_id FROM accounts AS ac1
		GROUP BY ac1.account_holder_id 
		HAVING sum_id > parameter AND ac1.account_holder_id = ah.id
	)
    GROUP BY ah.first_name,ah.last_name
    ORDER BY ac.id;
END;$$

## 12
DELIMITER $$
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(19,4))
BEGIN
START TRANSACTION;
IF (money_amount < 0 AND round(money_amount,4) <> money_amount ) THEN
ROLLBACK;
ELSE
UPDATE accounts AS a SET a.balance = a.balance  + money_amount WHERE a.id = account_id;
 
END IF;
END$$


#12
CREATE PROCEDURE usp_deposit_money (account_id INT, money_amount DECIMAL(14, 4))
BEGIN
	UPDATE accounts SET balance = balance + money_amount
    WHERE id = account_id;
END;


# 13
DELIMITER $$ 
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(14, 4))
BEGIN
	IF(SELECT ac.balance FROM accounts AS ac WHERE ac.id = account_id) - money_amount > 0 
    THEN UPDATE accounts SET balance = balance - money_amount
    WHERE id = account_id AND money_amount >= 0;
    END IF;
END;$$


##14
DELIMITER $$ 
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(20,4))
BEGIN
	START TRANSACTION;
		IF (SELECT id FROM accounts WHERE id = from_account_id) IS NULL
			OR(SELECT id FROM accounts WHERE id = to_account_id) IS NULL
            OR amount <0
            OR(SELECT balance - amount FROM accounts WHERE ID = from_account_id) < 0
            OR from_account_id = to_account_id
            THEN ROLLBACK;
		ELSE
			UPDATE accounts AS ac
            SET ac.balance = ac.balance - amount
            WHERE ac.id = from_account_id;
            
            UPDATE accounts AS ac1
            SET ac1.balance = ac1.balance + amount
            WHERE ac1.id = to_account_id;
		END IF;
COMMIT;
END$$

CALL usp_transfer_money(1,2,10);$$



#15
CREATE TABLE `logs`(
log_id INT PRIMARY KEY AUTO_INCREMENT,
account_id INT,
old_sum DECIMAL(14, 4),
new_sum DECIMAL(14, 4)
);$$
 
 
CREATE TRIGGER tr_change_balance
AFTER UPDATE
ON accounts
FOR EACH ROW
BEGIN
    INSERT INTO `logs`
    (account_id, old_sum, new_sum)
    VALUES
    (OLD.id, OLD.balance, NEW.balance);
END $$



#16
CREATE TABLE notification_emails(
id INT PRIMARY KEY AUTO_INCREMENT,
recipient INT,
`subject` VARCHAR(40),
body VARCHAR(255)
);$$
 
 
CREATE TRIGGER tr_notification_email
AFTER INSERT
ON `logs`
FOR EACH ROW
BEGIN
    INSERT INTO notification_emails
    (recipient, `subject`, body)
    VALUES
    (NEW.account_id, concat('Balance change for account: ', NEW.account_id),
    concat('On ', NOW(), ' your balance was changed from ', NEW.old_sum, ' to ', NEW.new_sum, '.'));
END $$



DELIMITER $$ 
CREATE PROCEDURE usp_get_holders_with_balance_higher_than (amount DOUBLE(14, 4))
BEGIN
	SELECT ah.first_name, ah.last_name FROM account_holders ah
	JOIN accounts a1
    ON ah.id = a1.account_holder_id
	WHERE EXISTS (
		SELECT  SUM(a.balance) sum_id FROM accounts a
		GROUP BY a.account_holder_id 
		HAVING sum_id > amount AND a.account_holder_id = ah.id
	)
    GROUP BY ah.first_name, ah.last_name
    ORDER BY a1.id;
END$$

DELIMITER $$
use soft_uni;$$
CALL usp_get_holders_with_balance_higher_than(7000);$$


DELIMITER $$
CREATE FUNCTION ufn_get_salary_level(salary DOUBLE(10,4))
RETURNS VARCHAR(8)
BEGIN
	DECLARE result VARCHAR(8);
	
    IF salary < 30000 THEN SET result := 'Low';
    ELSEIF salary BETWEEN 30000 AND 50000 THEN SET result := 'Average';
    ELSEIF salary > 50000 THEN SET result := 'High';
    END IF;
    
    RETURN result;
END$$

DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(level_of_salary VARCHAR(8))
BEGIN
	SELECT first_name,last_name
    FROM employyes 
    WHERE ufn_get_salary_level(salary) = level_of_salary
    ORDER BY first_name DESC,last_name DESC;
END$$


SELECT ufn_get_salary_level(13500.00);

USE soft_uni;


use soft_uni;

# CHEP

SELECT * FROM employees;

DELIMITER $$
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(20))
RETURNS DOUBLE
BEGIN
DECLARE e_count DOUBLE;
SET e_count := (SELECT COUNT(employee_id) FROM employees AS e
INNER JOIN addresses AS a ON a.address_id = e.address_id
INNER JOIN towns AS t ON t.town_id = a.town_id
WHERE t.name = town_name);
RETURN e_count;
END $$

DELIMITER $
CREATE TABLE deleted_employees(
employee_id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20),
last_name VARCHAR(20),
middle_name VARCHAR(20),
job_title VARCHAR(50),
department_id INT,
salary DOUBLE
);$



CREATE PROCEDURE usp_get_towns_starting_with(str_start VARCHAR(10))
BEGIN
	SELECT name AS `town_name` FROM towns
    WHERE name LIKE CONCAT(str_start, '%') 
    ORDER BY `town_name`;
END$



CALL usp_get_employees_salary_above_35000('b');


