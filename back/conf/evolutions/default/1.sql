-- !Ups

CREATE TABLE user_types (
	id SERIAL PRIMARY KEY,
	type VARCHAR(255) NOT NULL
)

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(255) NOT NULL UNIQUE,
	email VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	user_type INT REFERENCES user_types(id) NOT NULL
)

-- !Downs

DROP TABLE users;
DROP TABLE user_types;