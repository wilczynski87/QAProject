INSERT INTO address (id, city, country_code, country_name, county, county_code, district, house_number, label, lat, lng, postal_code, state, state_code, street)
VALUES (0, 'Worksop', 'country_code', 'country_name', 'county', 'county_code', 'district', '9', '9, Hardwick Road East, S80 2NS Worksop', 53.3019, -1.1025, 'S80 2NS', 'state', 'state_code', 'Hardwick Road East');
INSERT INTO address (id, city, country_code, country_name, county, county_code, district, house_number, label, lat, lng, postal_code, state, state_code, street)
VALUES (1, 'Worksop', 'country_code', 'country_name', 'county', 'county_code', 'district', '9', '9, Hardwick Road East, S80 2NS Worksop', 53.3019, -1.1025, 'S80 2NS', 'state', 'state_code', 'Hardwick Road East');
INSERT INTO address (id, city, country_code, country_name, county, county_code, district, house_number, label, lat, lng, postal_code, state, state_code, street)
VALUES (2, 'Worksop', 'country_code', 'country_name', 'county', 'county_code', 'district', '9', '9, Hardwick Road East, S80 2NS Worksop', 53.3019, -1.1025, 'S80 2NS', 'state', 'state_code', 'Hardwick Road East');
INSERT INTO address (id, city, country_code, country_name, county, county_code, district, house_number, label, lat, lng, postal_code, state, state_code, street)
VALUES (3, 'Worksop', 'country_code', 'country_name', 'county', 'county_code', 'district', '9', '9, Hardwick Road East, S80 2NS Worksop', 53.3019, -1.1025, 'S80 2NS', 'state', 'state_code', 'Hardwick Road East');

INSERT INTO consumer (user_address, email, firm, phone, full_name, password, id) 
VALUES (0, 'First@email.com', '', '1111-111-111', 'Firstofer Firstof', 'password1', '123e4567-e89b-12d3-a456-426614174000');

INSERT INTO professional (user_address, email, firm, full_name, password, phone, license_no, id) 
VALUES (1, 'Third@email.com', 'Firm3', 'Thirdofer Third', 'password3', '3333-333-333', '01', '223e4567-e89b-12d3-a456-426614174000'); 
INSERT INTO professional (email, password, id, user_address) 
VALUES ('email@email.com', 'password', '223e4567-e89b-12d3-a456-426614174001', 2);

INSERT INTO auction (id, f_key_consumer_id, auction_start, title, junk_type, volume, container_type, container_number, start_date, end_date, auction_address, note, expired) 
VALUES (0, '123e4567-e89b-12d3-a456-426614174000', '01/01/2022', 'junk from previous owners', 'mix', 3, '5m', 1, '31/01/2022', '07/02/2022', 0, 'leave container on the street', false);
INSERT INTO auction (id, f_key_consumer_id, auction_start, title, junk_type, volume, container_type, container_number, start_date, end_date, auction_address, note, expired) 
VALUES (1, '223e4567-e89b-12d3-a456-426614174000', '07/01/2022', 'junk from kitchen renewal', 'wood', 5, '5m', 1, '08/01/2022', '31/01/2022', 1, '', false);
INSERT INTO auction (id, f_key_consumer_id, auction_start, title, junk_type, volume, container_type, container_number, start_date, end_date, auction_address, note, expired) 
VALUES (2, '223e4567-e89b-12d3-a456-426614174000', '01/01/2022', 'junk from demolition', 'construction_debris', 100, 'truck_24t', 5, '31/03/2022', '07/04/2022', 2, 'no notes', true);

INSERT INTO bid (price, date, prof_firm, how_many_days, start_date, end_date, f_key_Professional_id, auction_id) 
VALUES (999, 'date', 'Firm3', 7, 'startDate', 'endDate', '223e4567-e89b-12d3-a456-426614174000', 1);
INSERT INTO bid (price, date, prof_firm, how_many_days, start_date, end_date, f_key_Professional_id, auction_id) 
VALUES (800, 'date', 'Firm3', 7, 'startDate', 'endDate', '223e4567-e89b-12d3-a456-426614174001', 1);
INSERT INTO bid (price, date, prof_firm, how_many_days, start_date, end_date, f_key_Professional_id, auction_id) 
VALUES (700, 'date', 'Firm3', 7, 'startDate', 'endDate', '223e4567-e89b-12d3-a456-426614174000', 1);
INSERT INTO bid (price, date, prof_firm, how_many_days, start_date, end_date, f_key_Professional_id, auction_id) 
VALUES (998, 'date', 'Firm3', 7, 'startDate', 'endDate', '223e4567-e89b-12d3-a456-426614174001', 2);



