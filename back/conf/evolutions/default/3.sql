-- !Ups

CREATE TABLE ingredients (
	id SERIAL PRIMARY KEY,
	ingredient VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE recipes (
	id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	description VARCHAR(65535) NOT NULL,
	number_of_portions INT NOT NULL
);

CREATE TABLE recipe_ingredients (
	id SERIAL PRIMARY KEY,
	recipe_id INT REFERENCES recipes(id) NOT NULL,
	ingredient_id INT REFERENCES ingredients(id) NOT NULL,
	measurement_id INT REFERENCES measurements(id) NOT NULL,
	quantity DECIMAL NOT NULL
);

-- !Downs

DROP TABLE recipe_ingredients;
DROP TABLE recipes;
DROP TABLE ingredients;
