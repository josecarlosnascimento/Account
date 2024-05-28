create table users (
id SERIAL,
password varchar(255),
role INT check (role between 0 and 1),
username varchar(255)
)