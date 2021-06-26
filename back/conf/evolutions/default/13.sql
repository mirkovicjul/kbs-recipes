-- !Ups

CREATE TABLE user_likes (
	user_id INT REFERENCES users(id) NOT NULL,
	ingredient_id INT REFERENCES ingredients(id) NOT NULL,
	CONSTRAINT pk_user_likes PRIMARY KEY (user_id, ingredient_id)
);

CREATE TABLE user_dislikes (
	user_id INT REFERENCES users(id) NOT NULL,
	ingredient_id INT REFERENCES ingredients(id) NOT NULL,
	CONSTRAINT pk_user_dislikes PRIMARY KEY (user_id, ingredient_id)
);

CREATE TABLE user_allergies (
	user_id INT REFERENCES users(id) NOT NULL,
	ingredient_id INT REFERENCES ingredients(id) NOT NULL,
	CONSTRAINT pk_user_allergies PRIMARY KEY (user_id, ingredient_id)
);

CREATE TABLE user_unavailable_ingredients (
	user_id INT REFERENCES users(id) NOT NULL,
	ingredient_id INT REFERENCES ingredients(id) NOT NULL,
	CONSTRAINT pk_user_unavailable_ingredients PRIMARY KEY (user_id, ingredient_id)
);

-- !Downs

DROP TABLE user_likes;
DROP TABLE user_dislikes;
DROP TABLE user_allergies;
DROP TABLE user_unavailable_ingredients;