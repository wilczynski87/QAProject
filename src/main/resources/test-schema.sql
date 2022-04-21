drop table if exists consumer CASCADE;
create table consumer (
id varchar(255),
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
id varchar(255),
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
F_KEY_CONSUMER_ID varchar(255),
auction_start varchar(255),
title varchar(255), 
junk_type varchar(255), 
volume int, 
container_type varchar(255), 
container_number int, 
start_date varchar(255), 
end_date varchar(255), 
address varchar(255), 
note varchar(255), 
primary key (id)
);

drop table if exists bid CASCADE;
create table bid (
id integer AUTO_INCREMENT,
price float,
f_key_Professional_id varchar(255),
auction_id int,
date varchar(255),
primary key (id)
);
