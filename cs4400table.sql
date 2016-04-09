use cs4400;
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

ALTER TABLE customer
	ADD CONSTRAINT UK_email UNIQUE(email);



CREATE TABLE paymentInfo(
	cardNum BIGINT PRIMARY KEY NOT NULL,
	cvv INT NOT NULL,
	expDate DATE NOT NULL,
    nameOnCard VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES customer(username)
);


ALTER TABLE paymentInfo
	ADD CONSTRAINT CK_cardNum CHECK (LENGTH(cardNum = 16));#check card number length;
ALTER TABLE paymentInfo ADD CONSTRAINT CK_cvv CHECK (LENGTH(cvv = 3)); # check cvv length;


CREATE TABLE reservation(
	reservationID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    isCanceled ENUM('true', 'false') DEFAULT 'false',
    username VARCHAR(50) NOT NULL,
	cardNum BIGINT NOT NULL,
    FOREIGN KEY (username) REFERENCES customer(username),
    FOREIGN KEY (cardNum) REFERENCES paymentInfo(cardNum)
);

select * from reservation;

CREATE TABLE trainRoute(
	trainNum VARCHAR(50) PRIMARY KEY NOT NULL,
    fClassPrice FLOAT NOT NULL,
    sClassPrice FLOAT NOT NULL
);

insert trainRoute values('2163 Express', 220, 115);
insert trainRoute values('2164 Express', 220, 115);
insert trainRoute values('2165 Express', 240, 120);


CREATE TABLE station (
	name VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    PRIMARY KEY (name, location)
);

insert station values('BBY', 'Boston');
insert station values('NL', 'New London');
select * from station;

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

select * from reserves;

select trainNum from reserves where reservationID = 3;
#select  from trianRoute where trainNum in(select trainNum from reserves where reservation = 3);
select trainNum, departureTime, arrivalTime, departsFrom, arrivesAt, class, numOfBaggages, passengerName, fClassPrice, sClassPrice from 
((reserves natural join(select trainNum, arrivalTime from stop where location in (select arrivesAt from reserves where reservationID = 9 and trainNum in
 (select trainNum from reserves where reservationID = 9) )and trainNum in (select trainNum from reserves where reservationID = 9)) as a)
 natural join (select trainNum, departureTime from stop where location 
in (select departsFrom from reserves where reservationID = 9 and trainNum in
 (select trainNum from reserves where reservationID = 9) )and trainNum in (select trainNum from reserves where reservationID = 9)) as c) natural join trainRoute;

select trainNum, arrivalTime from stop where location in (select arrivesAt from reserves where reservationID = 3 and trainNum in
 (select trainNum from reserves where reservationID = 3) )and trainNum in (select trainNum from reserves where reservationID = 3);
select trainNum, departureTime from stop where location 
in (select departsFrom from reserves where reservationID = 3 and trainNum in
 (select trainNum from reserves where reservationID = 3) )and trainNum in (select trainNum from reserves where reservationID = 3);
select trainNum, departureTime from stop where location in(select departsFrom from reserves where trainNum = '2163 Express' and reservationID = 3);
select  * from ((reserves natural join reservation) natural join stop);
update reserves set departsFrom = 'Boston' where reservationID = 3 and trainNum = '2163 Express';
update reserves set arrivesAt = 'New London' where reservationID = 3 and trainNum = '2163 Express';
select trainNum, departureTime from stop where location = 'Boston' and trainNum = '2163 Express' and name = 'BBY';
select trainNum, arrivalTime from stop where location = 'New London' and trainNum = '2163 Express' and name = 'NL';
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
	
select * from stop;
insert stop values('00:00:00', '11:25:00', 'BBY', 'Boston', '2163 Express' );
insert stop values ('11:50:00', '11:55:00', 'NL', 'New London', '2163 Express');
	
insert stop values('00:00:00', '11:25:00', 'BBY', 'Boston', '2164 Express' );
insert stop values ('11:50:00', '11:55:00', 'NL', 'New London', '2164 Express');
insert stop values('00:00:00', '11:25:00', 'BBY', 'Boston', '2165 Express' );
insert stop values ('11:50:00', '11:55:00', 'NL', 'New London', '2165 Express');

select trainNum, arrivalTime, departureTime, location, name from stop where trainNum = '2163 Express';


select if ((select arrivalTime from stop where name = 'BBY' and location = 'Boston') > (select departureTime from stop where name = 'NL' and location = 'New London'), 3, 2);

select * from (select * from (select trainNum, departureTime from stop  where name = 'BBY' and location = 'Boston') as a1 natural join (select trainNum, arrivalTime from stop where name = 'NL' and location = 'New London') as a2) as a3 natural join trainRoutes;



select fClassPrice, location from trainRoute T, stop S where T.trainNum = S.trainNum;

select * from trainRoute, stop;

select trainNum from trainRoute as t where not exists (select * from stop where trainNum = t.trainNum);

select count(T.trainNum) from (select trainNum from stop union select trainNum from trainRoute) as T;
