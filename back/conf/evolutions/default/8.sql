-- !Ups

ALTER TABLE recipes
	ADD days_before_expiration INT;

-- !Downs

ALTER TABLE recipes
	DROP COLUMN IF EXISTS days_before_expiration;
