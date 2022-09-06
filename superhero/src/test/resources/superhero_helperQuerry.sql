USE superhero_testDB;

SELECT * FROM member;
SELECT * FROM org;

SELECT p.* FROM person p JOIN member m ON m.person = p.id WHERE m.org = 2;