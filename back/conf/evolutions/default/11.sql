-- !Ups

ALTER TABLE recipes
	ADD preparation_time INT;

-- !Downs

ALTER TABLE recipes
	DROP COLUMN IF EXISTS preparation_time;