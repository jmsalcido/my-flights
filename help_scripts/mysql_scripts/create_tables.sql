DROP TABLE IF EXISTS airports;
CREATE TABLE reservations(
	id INT AUTO_INCREMENT PRIMARY KEY,
	price INT NOT NULL,
	name VARCHAR(50) NOT NULL,
	lastName VARCHAR(50) NOT NULL,
	telephone VARCHAR(15) NOT NULL,
	email VARCHAR(30) NOT NULL,
	departure VARCHAR(5) NOT NULL,
	arrival VARCHAR(5) NOT NULL,
	departureDate DATE NOT NULL,
	arrivalDate DATE NOT NULL
);
CREATE TABLE airports (
	id INT AUTO_INCREMENT PRIMARY KEY,
	fs VARCHAR(5) NOT NULL,
	iata VARCHAR(5) NOT NULL,
	icao VARCHAR(5) NOT NULL,
	faa VARCHAR(5) NOT NULL,
	name VARCHAR(255) NOT NULL,
	city VARCHAR(255) NOT NULL,
	countryCode VARCHAR(5) NOT NULL,
	countryName VARCHAR(30) NOT NULL,
	timeZoneRegionName VARCHAR(50) NOT NULL,
	utfOffsetHours INT NOT NULL,
	classification INT NOT NULL,
	latitude FLOAT(10,6) NOT NULL,
	longitude FLOAT(10,6) NOT NULL
);