create table users (
id SERIAL,
password varchar(255)  NOT NULL,
role INT check (role between 0 and 1)  NOT NULL,
username varchar(255)  NOT NULL
)