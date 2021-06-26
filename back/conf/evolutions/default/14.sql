-- !Ups

CREATE TABLE recipe_tags (
	id SERIAL PRIMARY KEY,
	recipe_id INT REFERENCES recipes(id) NOT NULL,
	tag VARCHAR(255) NOT NULL
);

-- !Downs

DROP TABLE recipe_tags;