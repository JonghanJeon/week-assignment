DROP TABLE IF EXISTS Users;

CREATE TABLE Users
(
    id   VARCHAR(10) PRIMARY KEY,
    name VARCHAR(10) NOT NULL
);

INSERT INTO Users
VALUES ('ms', 'minseok');
INSERT INTO Users
VALUES ('jh', 'jonghan');

SELECT *
FROM Users;

SELECT *
FROM Users
WHERE id = 'ms';

UPDATE Users
SET name='seokmin'
WHERE id = 'ms';

SELECT *
FROM Users;

DELETE
FROM Users;

SELECT *
FROM Users;