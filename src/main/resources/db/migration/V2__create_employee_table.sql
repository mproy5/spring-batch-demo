CREATE TABLE employees
(
    employee_id       VARCHAR(255) NOT NULL PRIMARY KEY,
    first_name        VARCHAR(255),
    last_name         VARCHAR(255),
    email             VARCHAR(255),
    date_of_birth     DATE,
    age               INTEGER
);