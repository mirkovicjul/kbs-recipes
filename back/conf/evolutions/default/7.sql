-- !Ups

CREATE TABLE ingredient_storage (
	id SERIAL PRIMARY KEY,
	user_id INT references users(id) not null,
	ingredient_id INT references ingredients(id) not null,
	quantity DECIMAL not null,
	measurement INT references measurements(id),
	best_before TIMESTAMP
);

CREATE TABLE recipe_storage (
	id SERIAL PRIMARY KEY,
	user_id INT references users(id) not null,
	recipe_id INT references recipes(id) not null,
	servings INT,
	best_before TIMESTAMP
);

CREATE TABLE history (
	id SERIAL PRIMARY KEY,
	user_id INT references users(id) not null,
	recipe_id INT references recipes(id) not null,
	servings INT
);

-- !Downs

DROP TABLE ingredient_storage;
DROP TABLE recipe_storage;
DROP TABLE history;