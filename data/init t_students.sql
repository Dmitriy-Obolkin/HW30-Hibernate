SHOW DATABASES;
USE hillel_hw;
SHOW TABLES;

CREATE TABLE t_students(
     id INT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(128)  NOT NULL,
     email VARCHAR(128) NOT NULL
);

DROP TABLE t_students;

/*INSERT INTO t_students (name, email) VALUES
('Alice', 'alice@email.com'),
('Bob', 'bob@email.com'),
('John', 'John@email.com'),
('David', 'david@email.com'),
('Emily', 'emily@email.com'),
('Frank', 'frank@email.com'),
('Grace', 'grace@email.com'),
('Helen', 'helen@email.com'),
('Ivy', 'ivy@email.com'),
('Jack', 'jack@email.com'),
('Kelly', 'kelly@email.com'),
('Linda', 'linda@email.com'),
('Mike', 'mike@email.com'),
('Nancy', 'nancy@email.com'),
('Oscar', 'oscar@email.com');*/

SELECT *
FROM t_students;

