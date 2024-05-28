create table users (
id bigint  PRIMARY KEY auto_increment,
password varchar(255),
role tinyint check (role between 0 and 1),
username varchar(255)
)