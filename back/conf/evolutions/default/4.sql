-- !Ups

ALTER TABLE ingredients
	ADD fats DECIMAL(10,2),
	ADD carbs DECIMAL(10,2),
	ADD protein DECIMAL(10,2),
	ADD type VARCHAR(255);

-- !Downs

ALTER TABLE ingredients
	DROP COLUMN IF EXISTS fats,
	DROP COLUMN IF EXISTS carbs,
	DROP COLUMN IF EXISTS protein,
	DROP COLUMN IF EXISTS type;
