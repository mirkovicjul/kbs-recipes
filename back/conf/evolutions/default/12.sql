-- !Ups

CREATE TABLE recipe_images (
	id SERIAL PRIMARY KEY,
	recipe_id INT REFERENCES recipes(id) NOT NULL,
	path VARCHAR(255) NOT NULL
);

-- !Downs

DROP TABLE recipe_images;
