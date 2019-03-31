AccountsDB Project

The Database should be running before deploying the application. The database name and URL is

database name = accountsdb
database URL = jdbc:mysql://localhost:3306/accountsdb?autoReconnect=true&amp;useSSL=false"

Create the database 
The SQL code to create the database and insert some values are given below

CREATE TABLE accountsdb.ACCOUNTS (
id INT NOT NULL AUTO_INCREMENT, 
account_number VARCHAR(255), 
account_type CHAR(255), 
PRIMARY KEY (id));

CREATE TABLE accountsdb.STATEMENTS (
id INT NOT NULL AUTO_INCREMENT, 
account_id INT, 
datefield DATETIME, 
amount DOUBLE, 
PRIMARY KEY (id),
FOREIGN KEY (account_id) REFERENCES ACCOUNTS(id));

INSERT INTO accountsdb.ACCOUNTS (account_number, account_type) VALUES ('1233333', 'Savings');
INSERT INTO accountsdb.ACCOUNTS (account_number, account_type) VALUES ('34563421', 'Fixed Deposit');
INSERT INTO accountsdb.ACCOUNTS (account_number, account_type) VALUES ('768613325', 'Current');

INSERT INTO accountsdb.STATEMENTS (id, account_id, datefield, amount) VALUES (1, 1, '2019-03-27', 600.0);
INSERT INTO accountsdb.STATEMENTS (id, account_id, datefield, amount) VALUES (2, 2, '2019-03-27', 500.0);
INSERT INTO accountsdb.STATEMENTS (id, account_id, datefield, amount) VALUES (3, 3, '2019-03-27', 700.0);


To access the restful service endpoint, visit localhost:8080/accountsdb/webresources/statements after successfully deploying the application

