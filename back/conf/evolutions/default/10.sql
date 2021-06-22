-- !Ups

ALTER TABLE ingredient_storage 
RENAME COLUMN measurement TO measurement_id;

-- !Downs

ALTER TABLE ingredient_storage
	DROP COLUMN IF EXISTS measurement_id;
