#all data in tables are use for test
use cs4400
CREATE TABLE user(
	username VARCHAR(50) PRIMARY KEY NOT NULL,
	password VARCHAR(50) NOT NULL,
    userType VARCHAR(50) NOT NULL
)

CREATE TABLE customer(
	username VARCHAR(50) PRIMARY KEY NOT NULL,
	email VARCHAR(50) NOT NULL,
	isStudent enum('true', 'false') DEFAULT 'false',
    FOREIGN KEY (username) REFERENCES user(username)
)

ALTER TABLE customer
	ADD CONSTRAINT UK_email UNIQUE(email)



CREATE TABLE KEY paymentInfo(
	cardNum BIGINT PRIMARY KEY NOT NULL,
	cvv INT NOT NULL,
	expDate DATE NOT NULL,
    nameOnCard VARCHAR(50) NOT NULL,
    username VARCHAR(50),
    FOREIGN KEY (username) REFERENCES customer(username)
)


ALTER TABLE paymentInfo
	ADD CONSTRAINT CK_cardNum CHECK (LENGTH(cardNum = 16))#check card number length
ALTER TABLE paymentInfo ADD CONSTRAINT CK_cvv CHECK (LENGTH(cvv = 3)) # check cvv length


CREATE TABLE reservation(
	reservationID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    isCanceled ENUM('true', 'false') DEFAULT 'false',
    username VARCHAR(50) NOT NULL,
	cardNum BIGINT NOT NULL,
    FOREIGN KEY (username) REFERENCES customer(username),
    FOREIGN KEY (cardNum) REFERENCES paymentInfo(cardNum)
)

CREATE TABLE trainRoute(
	trainNum VARCHAR(50) PRIMARY KEY NOT NULL,
    fClassPrice FLOAT NOT NULL,
    sClassPrice FLOAT NOT NULL
)

insert trainRoute values('2163 Express', 220, 115)


CREATE TABLE station (
	name VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    PRIMARY KEY (name, location)
)

insert station values('BBY', 'Boston')
insert station values('NL', 'New London')

CREATE TABLE review(
	reviewNum INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    comment TEXT,
    rating ENUM('very good', 'good', 'neutral', 'bad', 'very bad') default 'good',
    trainNum VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    FOREIGN KEY (trainNum) REFERENCES trainRoute(trainNum),
    FOREIGN KEY (username) REFERENCES customer(username)
)


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
)

CREATE TABLE stop(
	arrivalTime TIME NOT NULL,
    departureTime TIME NOT NULL,
    name VARCHAR(50) NOT NULL,
	location VARCHAR(50) NOT NULL,
    trainNum VARCHAR(50) NOT NULL,
    PRIMARY KEY (name, location, trainNum),
    FOREIGN KEY (name, location) REFERENCES station (name, location),
    FOREIGN KEY (trainNum) REFERENCES trainRoute(trainNum)
)
	
insert stop values('00:00:00', '11:25:00', 'BBY', 'Boston', '2163 Express' )
insert stop values ('11:50:00', '11:55:00', 'NL', 'New London', '2163 Express')
select trainNum, arrivalTime, departureTime, location, name from stop where trainNum = '2163 Express'

