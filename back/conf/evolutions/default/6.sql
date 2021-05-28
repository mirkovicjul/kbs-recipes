-- !Ups

ALTER TABLE recipes
	ADD vegan BOOLEAN,
	ADD vegetarian BOOLEAN,
	ADD junk_food BOOLEAN;

-- !Downs

ALTER TABLE recipes
	DROP COLUMN IF EXISTS vegan,
	DROP COLUMN IF EXISTS vegetarian,
	DROP COLUMN IF EXISTS junk_food;