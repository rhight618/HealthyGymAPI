DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL
);

INSERT INTO users (first_name, last_name) VALUES
  ('Ryan', 'Hightower'),
  ('John', 'Smith'),
  ('Bob', 'Jones');
  
DROP TABLE IF EXISTS checkins;

CREATE TABLE checkins (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id INT NOT NULL,
  checkin_timestamp TIMESTAMP NOT NULL
);

INSERT INTO checkins (user_id, checkin_timestamp) VALUES
  (1, '2021-10-01 12:00:00');
  
DROP TABLE IF EXISTS selfreportings;

CREATE TABLE selfreportings (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id INT NOT NULL,
  fourteen_days BOOLEAN NOT NULL,
  positive_test BOOLEAN NOT NULL,
  report_timestamp TIMESTAMP NOT NULL
);

INSERT INTO selfreportings (user_id, fourteen_days, positive_test, report_timestamp) VALUES
  (1, 0, 0, '2021-10-01 12:00:00');