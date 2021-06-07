-- !Ups

ALTER TABLE history
	ADD date TIMESTAMP;

-- !Downs

ALTER TABLE history
	DROP COLUMN IF EXISTS date;
