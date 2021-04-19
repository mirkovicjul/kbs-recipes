-- !Ups

CREATE TABLE measurements (
	id INT PRIMARY KEY,
	measurement VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE measurement_converter (
	id INT PRIMARY KEY,
	from_measurement INT REFERENCES measurements(id),
	to_measurement INT REFERENCES measurements(id),
	factor DECIMAL
);

-- !Downs

DROP TABLE measurement_converter;
DROP TABLE measurements;
