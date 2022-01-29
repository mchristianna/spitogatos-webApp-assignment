
create database realestate;
use realestate;

CREATE TABLE users (userid INT NOT NULL AUTO_INCREMENT PRIMARY KEY, username VARCHAR(200), password VARCHAR(200), table_name VARCHAR(200));

INSERT INTO users (username, password, table_name) VALUES ('Maria', 'strongpass12', 'properties1');
INSERT INTO users (username, password, table_name) VALUES ('Dafni', 'strongpass34','properties2');
INSERT INTO users (username, password, table_name) VALUES ('Kostas', 'strongpass56', 'properties3');
INSERT INTO users (username, password, table_name) VALUES ('Steve', 'strongpass78', 'properties4');
INSERT INTO users (username, password, table_name) VALUES ('Mary', 'strongpass910', 'properties5');


CREATE TABLE properties1 (id INT NOT NULL AUTO_INCREMENT, userid INT NOT NULL, area VARCHAR(200) NOT NULL, price INT(7) NOT NULL, availability VARCHAR(200) NOT NULL, squareMeters INT(4) NOT NULL, PRIMARY KEY(id, userid));
CREATE TABLE properties2 (id INT NOT NULL AUTO_INCREMENT, userid INT NOT NULL, area VARCHAR(200) NOT NULL, price INT(7) NOT NULL, availability VARCHAR(200) NOT NULL, squareMeters INT(4) NOT NULL, PRIMARY KEY(id, userid));
CREATE TABLE properties3 (id INT NOT NULL AUTO_INCREMENT, userid INT NOT NULL, area VARCHAR(200) NOT NULL, price INT(7) NOT NULL, availability VARCHAR(200) NOT NULL, squareMeters INT(4) NOT NULL, PRIMARY KEY(id, userid));
CREATE TABLE properties4 (id INT NOT NULL AUTO_INCREMENT, userid INT NOT NULL, area VARCHAR(200) NOT NULL, price INT(7) NOT NULL, availability VARCHAR(200) NOT NULL, squareMeters INT(4) NOT NULL, PRIMARY KEY(id, userid));
CREATE TABLE properties5 (id INT NOT NULL AUTO_INCREMENT, userid INT NOT NULL, area VARCHAR(200) NOT NULL, price INT(7) NOT NULL, availability VARCHAR(200) NOT NULL, squareMeters INT(4) NOT NULL, PRIMARY KEY(id, userid));


INSERT INTO properties1 (userid, area, price, availability, squareMeters) VALUES ('1','Athens','500','rent','80');
INSERT INTO properties1 (userid, area, price, availability, squareMeters) VALUES ('1','Thessaloniki','800','rent','100');
INSERT INTO properties1 (userid, area, price, availability, squareMeters) VALUES ('1','Athens','500','rent','80');
INSERT INTO properties1 (userid, area, price, availability, squareMeters) VALUES ('1','Athens','150000','sell','80');
INSERT INTO properties1 (userid, area, price, availability, squareMeters) VALUES ('1','Patra','900','rent','150');
INSERT INTO properties1 (userid, area, price, availability, squareMeters) VALUES ('1','Athens','600','rent','80');


INSERT INTO properties2 (userid, area, price, availability, squareMeters) VALUES ('2','Athens','500','rent','80');
INSERT INTO properties2 (userid, area, price, availability, squareMeters) VALUES ('2','Athens','150000','sell','80');
INSERT INTO properties2 (userid, area, price, availability, squareMeters) VALUES ('2','Patra','900','rent','150');
INSERT INTO properties2 (userid, area, price, availability, squareMeters) VALUES ('2','Athens','600','rent','80');
INSERT INTO properties2 (userid, area, price, availability, squareMeters) VALUES ('2','Thessaloniki','800','rent','100');
INSERT INTO properties2 (userid, area, price, availability, squareMeters) VALUES ('2','Athens','500','rent','80');



INSERT INTO properties3 (userid, area, price, availability, squareMeters) VALUES ('3','Athens','500','rent','80');
INSERT INTO properties3 (userid, area, price, availability, squareMeters) VALUES ('3','Thessaloniki','800','rent','100');
INSERT INTO properties3 (userid, area, price, availability, squareMeters) VALUES ('3','Athens','500','rent','80');



INSERT INTO properties4 (userid, area, price, availability, squareMeters) VALUES ('4','Athens','150000','sell','80');
INSERT INTO properties4 (userid, area, price, availability, squareMeters) VALUES ('4','Patra','900','rent','150');
INSERT INTO properties4 (userid, area, price, availability, squareMeters) VALUES ('4','Athens','600','rent','80');

/*properties5 check initial insert from page*/


SELECT * FROM properties2;
DELETE FROM properties2 WHERE (id, userid) = ('','2');




