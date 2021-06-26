-- !Ups

ALTER TABLE recipes ADD COLUMN image VARCHAR(255);

-- !Downs

ALTER TABLE recipes DROP COLUMN IF EXISTS image;
