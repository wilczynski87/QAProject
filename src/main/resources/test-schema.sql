drop table if exists consumer CASCADE;
create table consumer (
id varchar(255),
user_address int, 
email varchar(255), 
firm varchar(255), 
full_name varchar(255), 
password varchar(255), 
phone varchar(255),
primary key (id)
);

drop table if exists professional CASCADE;
create table professional (
id varchar(255),
user_address integer, 
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
f_key_consumer_id varchar(255),
auction_start varchar(255),
title varchar(255), 
junk_type varchar(255), 
volume int, 
container_type varchar(255), 
container_number int, 
start_date varchar(255), 
end_date varchar(255), 
auction_address int, 
note varchar(255), 
expired bit(1),
primary key (id)
);

drop table if exists bid CASCADE;
create table bid (
id integer AUTO_INCREMENT,
price float,
f_key_Professional_id varchar(255),
auction_id int,
date varchar(255),
prof_firm varchar(255),
how_many_days int,
start_date varchar(255),
end_date varchar(255),
primary key (id)
);

drop table if exists address CASCADE;
create table address (
id integer AUTO_INCREMENT,
city varchar(255),
country_code varchar(255),
country_name varchar(255),
county varchar(255),
county_code varchar(255),
district varchar(255),
house_number varchar(255),
label varchar(255),
lat double,
lng double,
postal_code varchar(255),
state varchar(255),
state_code varchar(255),
street varchar(255),
primary key (id)
);



