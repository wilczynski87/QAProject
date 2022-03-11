drop table if exists consumer CASCADE;
create table consumer (
id integer AUTO_INCREMENT,
address varchar(255), 
email varchar(255), 
firm varchar(255), 
full_name varchar(255), 
password varchar(255), 
phone varchar(255),
primary key (id)
);

drop table if exists professional CASCADE;
create table professional (
id integer AUTO_INCREMENT,
address varchar(255), 
email varchar(255), 
firm varchar(255), 
full_name varchar(255), 
password varchar(255), 
phone varchar(255), 
license_no varchar(255),
primary key (id)
);

drop table if exists auction CASCADE;
create table auction (
id integer AUTO_INCREMENT,
F_KEY_CONSUMER_ID int, 
primary key (id)
);

drop table if exists bid CASCADE;
create table bid (
id integer AUTO_INCREMENT,
price float,
f_key_Professional_id int,
auction_id int,
primary key (id)
);
