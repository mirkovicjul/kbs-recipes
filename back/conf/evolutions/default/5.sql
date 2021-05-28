-- !Ups

ALTER TABLE measurements
	ADD proportion DECIMAL(10,2);

-- !Downs

ALTER TABLE measurements
	DROP COLUMN IF EXISTS proportion;