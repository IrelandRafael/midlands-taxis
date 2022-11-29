DROP DATABASE IF EXISTS midlands_taxis;
CREATE DATABASE midlands_taxis;

USE midlands_taxis;

CREATE TABLE staff (
    id int NOT NULL auto_increment,
    firstName varchar(255) NOT NULL,
    lastName varchar(255) NOT NULL,
    username varchar(25) NOT NULL,
    password varchar(25) NOT NULL,
    email varchar(255) NOT NULL,
    category enum('MANAGEMENT', 'FRONT_DESK', 'DRIVER') NOT NULL, # this is a contraint, which limits the values for the category column    
    PRIMARY KEY (id),
	UNIQUE (username), #this is a constraint (the system won't accept 2 users with the same username)
	UNIQUE (email)  # #this is a constraint (the system won't accept 2 users with the same email)
);

CREATE TABLE bookings (
	id int NOT NULL auto_increment,
	booking_no varchar(15) NOT NULL,
	pickup_location varchar(255) NOT NULL,
	drop_location varchar(255),
	pickup_time TIMESTAMP,
	PRIMARY KEY (id),
	UNIQUE (booking_no)
);

CREATE TABLE driver_journeys (
	id int NOT NULL auto_increment,
    journey_status enum('STARTED', 'COMPLETED', 'PENDING') NOT NULL,
    journey_startedTime TIMESTAMP, 
    journey_completedTime TIMESTAMP, 
    driver_id int NOT NULL, 
    booking_id int NOT NULL,
    PRIMARY KEY (id),
	FOREIGN KEY (driver_id) REFERENCES staff(id),
    FOREIGN KEY (booking_id) REFERENCES bookings(id),
    UNIQUE (booking_id)
);




