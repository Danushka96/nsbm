CREATE TABLE 'users' (
  'username' varchar(10) NOT NULL,
  'password' varchar(12) NOT NULL
);

ALTER TABLE 'users'
  ADD primary key ('username');
COMMIT;