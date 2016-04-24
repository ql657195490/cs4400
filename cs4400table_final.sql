USE cs4400;

CREATE TABLE user(
	username VARCHAR(50) PRIMARY KEY NOT NULL,
	password VARCHAR(50) NOT NULL,
    userType VARCHAR(50) NOT NULL
);

CREATE TABLE customer(
	username VARCHAR(50) PRIMARY KEY NOT NULL,
	email VARCHAR(50) NOT NULL,
	isStudent enum('true', 'false') DEFAULT 'false',
    FOREIGN KEY (username) REFERENCES user(username)
);

CREATE TABLE paymentInfo(
	cardNum BIGINT PRIMARY KEY NOT NULL,
	cvv INT NOT NULL,
	expDate DATE NOT NULL,
    nameOnCard VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES customer(username)
);



CREATE TABLE reservation(
	reservationID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    isCanceled ENUM('true', 'false') DEFAULT 'false',
    username VARCHAR(50) NOT NULL,
	cardNum BIGINT NOT NULL,
    isUpdated ENUM('true', 'false') DEFAULT 'false',
    totalCost FLOAT DEFAULT 0,
    FOREIGN KEY (username) REFERENCES customer(username),
    FOREIGN KEY (cardNum) REFERENCES paymentInfo(cardNum)
);

CREATE TABLE trainRoute(
	trainNum VARCHAR(50) PRIMARY KEY NOT NULL,
    fClassPrice FLOAT NOT NULL,
    sClassPrice FLOAT NOT NULL
);

CREATE TABLE station (
	name VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    PRIMARY KEY (name, location)
);

CREATE TABLE review(
	reviewNum INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    comment TEXT,
    rating ENUM('very good', 'good', 'neutral', 'bad', 'very bad') default 'good',
    trainNum VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    FOREIGN KEY (trainNum) REFERENCES trainRoute(trainNum),
    FOREIGN KEY (username) REFERENCES customer(username)
);

CREATE TABLE reserves (
	class VARCHAR(10) NOT NULL,
    departureDate DATE NOT NULL,
    passengerName VARCHAR(50) NOT NULL,
    numOfBaggages INT NOT NULL DEFAULT 0, 
    departsFrom VARCHAR(50) NOT NULL,
    arrivesAt VARCHAR(50) NOT NULL,
    reservationID INT NOT NULL,
    trainNum VARCHAR(50) NOT NULL,
    PRIMARY KEY (reservationID, trainNum),
    FOREIGN KEY (reservationID) REFERENCES reservation(reservationID),
	FOREIGN KEY (trainNum) REFERENCES trainRoute(trainNum)
);

CREATE TABLE stop(
	arrivalTime TIME NOT NULL,
    departureTime TIME NOT NULL,
    name VARCHAR(50) NOT NULL,
	location VARCHAR(50) NOT NULL,
    trainNum VARCHAR(50) NOT NULL,
    PRIMARY KEY (name, location, trainNum),
    FOREIGN KEY (name, location) REFERENCES station (name, location),
    FOREIGN KEY (trainNum) REFERENCES trainRoute(trainNum)
);

ALTER TABLE paymentInfo
	ADD CONSTRAINT CK_cardNum CHECK (LENGTH(cardNum = 16));#check card number length;
ALTER TABLE paymentInfo ADD CONSTRAINT CK_cvv CHECK (LENGTH(cvv = 3)); # check cvv length;

SELECT * FROM user;

SELECT * FROM customer;

SELECT * FROM paymentInfo;

SELECT * FROM trainRoute;

SELECT * FROM station;

SELECT * FROM stop;
#insert values to the table user
INSERT user values('c1', '1234567890-', 'customer'),
	('c2', '1234567890-', 'customer'),
    ('c3', '1234567890-', 'customer'),
    ('c4', '1234567890-', 'customer'),
    ('c5', '1234567890-', 'customer'),
    ('c6', '1234567890-', 'customer'),
    ('c7', '1234567890-', 'customer'),
    ('c8', '1234567890-', 'customer'),
    ('c9', '1234567890-', 'customer'),
    ('c10', '1234567890-', 'customer'),
    ('c11', '1234567890-', 'customer'),
    ('c12', '1234567890-', 'customer'),
    ('c13', '1234567890-', 'customer'),
    ('c14', '1234567890-', 'customer'),
    ('c15', '1234567890-', 'customer'),
    ('c16', '1234567890-', 'customer'),
    ('c17', '1234567890-', 'customer'),
    ('c18', '1234567890-', 'customer'),
    ('c19', '1234567890-', 'customer'),
    ('c20', '1234567890-', 'customer'),
    ('m1', '1234567890-', 'manager'),
    ('m2', '1234567890-', 'manager'),
    ('m3', '1234567890-', 'manager'),
    ('m4', '1234567890-', 'manager'),
    ('m5', '1234567890-', 'manager');
    
#insert values to the table customer
INSERT customer VALUES('c1', 'hanmm123@hotmail.com', 'true'),
	('c2', 'lilei234@hotmail.com', 'true'),
    ('c3', 'wuhan256@hotmail.com', 'true'),
    ('c4', 'gaof716@hotmail.com', 'true'),
    ('c5', 'linss221@hotmail.com', 'true'),
    ('c6', 'wanggai187@hotmail.com', 'true'),
    ('c7', 'chrisrobert665@hotmail.com', 'true'),
    ('c8', 'franklinw889@hotmail.com', 'true'),
    ('c9', 'Michaelab772@hotmail.com', 'true'),
    ('c10', 'chenalen654@hotmail.com', 'true'),
    ('c11', 'bob@gmail.com', 'false'),
    ('c12', 'hjames@gmail.com', 'false'),
    ('c13', 'mlouis@gmail.com', 'false'),
    ('c14', 'xixi@gmail.com', 'false'),
    ('c15', 'ironman@gmail.com', 'false'),
    ('c16', 'throne@gamil.com', 'false'),
    ('c17', 'leader@gmail.com', 'false'),
    ('c18', 'hulk@gmail.com', 'false'),
    ('c19', 'superman@gmail.com', 'false'),
    ('c20', 'batman@gmail.com', 'false');
    
#insert values to table paymentInfo
INSERT paymentInfo VALUES(4783679087666520, 756, '2017-05-01', 'MEIMEI HAN', 'c1'),
	(4783626256872450, 554, '2018-06-01', 'LEI LI', 'c1'),
    (4783456787923560, 332, '2019-07-01', 'HAN WU', 'c2'),
    (4783423566896740, 236, '2017-09-01', 'FENG GAO', 'c2'),
    (4783456213347890, 367, '2017-01-01', 'SANSAN LIN', 'c3'),
    (4783456678332450, 787, '2019-09-01', 'GAI WANG', 'c3'),
    (4783678977609830, 226, '2019-06-01', 'ROBERT CHRIS', 'c1'),
    (4783564544637860, 155, '2018-08-01', 'WATER FRANKLIN', 'c4'),
    (4783876232367990, 337, '2018-02-01', 'ABEL MICHAEL', 'c5'),
    (4783456267895430, 443, '2017-03-01', 'ALEN CHEN', 'c6'),
    (4996251967836550, 234, '2018-07-01', 'BOB STEVEN', 'c7'),
    (4992453346787860, 767, '2017-05-01', 'HADEN JAMES', 'c8'),
    (4992346778342150, 589, '2018-04-01', 'LOUIS MICHEL', 'c1'),
    (4992156731452230, 912, '2015-03-01', 'DADA XI', 'c11'),
    (4992453456987980, 662, '2018-02-01', 'TONY PARK', 'c11'),
    (4992315892345150, 115, '2019-02-01', 'THRONE GOD', 'c11'),
    (4992378198736760, 323, '2018-12-01', 'CAPTIAN AMERICAN', 'c12'),
    (4992789156346650, 479, '2017-10-01', 'ERIC BANA', 'c12'),
    (4992889654897650, 109, '2019-09-01', 'CLARK KENT', 'c12'),
    (4992136797223210, 954, '2018-11-01', 'BRUCE WAYNE', 'c13'),
    (4992477766334980, 991, '2018-10-01', 'DEAD POOL', 'c14'),
    (4992134213678650, 772, '2018-04-01', 'JOKER HALEN', 'c15'),
    (4992199867678830, 456 ,'2019-12-01', 'MYSTIQUE HUANG', 'c16'),
    (4992774536900980, 327, '2017-06-01', 'BEAST TOMES', 'c17'),
    (4992876549900850, 220, '2018-07-01', 'MEGNETO SWEET', 'c17');
    
#insert values to table trainRoute
INSERT trainRoute VALUES('adc728102', 200, 105),
	('akw392121', 220, 115),
    ('kwa762812', 240, 125),
    ('dca092182', 280, 145),
    ('kwr328937', 300, 155),
    ('rkw812931', 320, 165),
    ('kwc128399', 330, 170),
    ('ckw423874', 350, 175);

#insert values to table station
INSERT station VALUES('A1', 'Atlanta'),
	('C1', 'Columbia'),
    ('R1', 'Raleigh'),
    ('WD1', 'Washington.DC'),
    ('O1', 'Orlando'),
    ('M1', 'Miami'),
    ('KW1', 'Key West');
    
 #inservalues to table stop
 INSERT stop VALUES('00:00:00', '09:00:00', 'A1', 'Atlanta', 'adc728102'),
		         ('10:20:00', '10:35:00', 'C1', 'Columbia', 'adc728102'),
    ('11:50:00', '12:05:00', 'R1', 'Raleigh', 'adc728102'),
    ('12:50:00', '00:00:00', 'WD1', 'Washington.DC', 'adc728102'),
    ('00:00:00', '07:00:00', 'A1', 'Atlanta', 'akw392121'),
    ('08:30:00', '08:45:00', 'O1', 'Orlando', 'akw392121'),
    ('09:35:00', '09:50:00', 'M1', 'Miami', 'akw392121'),
    ('10:40:00', '00:00:00', 'KW1', 'Key West', 'akw392121'),
    ('00:00:00', '11:00:00', 'KW1', 'Key West', 'kwa762812'),
    ('11:50:00', '12:05:00', 'M1', 'Miami', 'kwa762812'),
    ('12:55:00', '13:10:00', 'O1', 'Orlando', 'kwa762812'),
    ('14:40:00', '00:00:00', 'A1', 'Atlanta', 'kwa762812'),
    ('00:00:00', '13:30:00', 'WD1', 'Washington.DC', 'dca092182'),
    ('14:15:00', '14:30:00', 'R1', 'Raleigh', 'dca092182'),
    ('15:45:00', '16:00:00', 'C1', 'Columbia', 'dca092182'),
    ('17:20:00', '00:00:00', 'A1', 'Atlanta', 'dca092182'),	
    ('00:00:00', '09:00:00', 'KW1', 'Key West', 'kwr328937'),
    ('10:10:00', '10:25:00', 'O1', 'Orlando', 'kwr328937'),
    ('11:40:00', '12:00:00', 'C1', 'Columbia', 'kwr328937'),
    ('12:50:00', '00:00:00', 'R1', 'Raleigh', 'kwr328937'),
    ('00:00:00', '12:30:00', 'R1', 'Raleigh', 'rkw812931'),
    ('13:20:00', '13:35:00', 'C1', 'Columbia', 'rkw812931'),
    ('15:00:00', '15:15:00', 'O1', 'Orlando', 'rkw812931'),
    ('16:05:00', '00:00:00', 'KW1', 'Key West', 'rkw812931'),
    ('00:00:00', '05:00:00', 'KW1', 'Key West', 'kwc128399'),
    ('06:00:00', '06:15:00', 'M1', 'Miami', 'kwc128399'),
    ('07:35:00', '07:50:00', 'A1', 'Atlanta', 'kwc128399'),
    ('09:00:00', '00:00:00', 'C1', 'Columbia', 'kwc128399'),
    ('00:00:00', '09:45:00', 'C1', 'Columbia', 'ckw423874'),
    ('11:05:00', '11:20:00', 'A1', 'Atlanta', 'ckw423874'),
    ('12:35:00', '12:50:00', 'M1', 'Miami', 'ckw423874'),
    ('13:40:00', '00:00:00', 'KW1', 'Key West', 'ckw423874');
    
SELECT  * FROM ((SELECT departureTime, trainNum FROM stop WHERE name = 'A1' AND location = 'Atlanta' ) AS a1 NATURAL JOIN
(SELECT arrivalTime, trainNum From stop WHERE name = 'C1' AND location ='Columbia') AS a2) NATURAL JOIN trainRoute WHERE arrivalTime > departureTime AND departureTime != '00:00:00';

#SELECT expDate FROM paymentInfo WHERE cardNum = 1;