INSERT INTO consumer (address, phone, email, full_name, password, firm) VALUES ('10 First street S11 1SS London', '1111-111-111', 'First@email.com', 'Firstofer Firstof', 'password1', '');
INSERT INTO consumer (address, phone, email, full_name, password, firm) VALUES ('20 Second street S22 2SS London', '2222-222-222', 'Second@email.com', 'Secendorf Secend', 'password2', 'Leitz');
INSERT INTO consumer (address, phone, email, full_name, password, firm) VALUES ('30 Third street S33 3SS London', '3333-333-333', 'Third@email.com', 'Thirdofer Third', 'password3', 'Daccar');
INSERT INTO consumer (email, password) VALUES ('email@email.com', 'password');


INSERT INTO professional (firm, address, phone, email, full_name, password, license_no) VALUES ('Firm3', '30 Third street S33 3SS London', '3333-333-333', 'Third@email.com', 'Thirdofer Third', 'password3', 'lisdbflbc');
INSERT INTO professional (firm, address, phone, email, full_name, password, license_no) VALUES ('Firm3', '20 Second street S22 2SS London', '2222-222-222', 'Second@email.com', 'Secendorf Secend', 'password2', 'lijbdfkljq');
INSERT INTO professional (firm, address, phone, email, full_name, password, license_no) VALUES ('Firm3', '30 Third street S33 3SS London', '3333-333-333', 'Third@email.com', 'Thirdofer Third', 'password3', 'xqasdliuq');
INSERT INTO professional (email, password) VALUES ('email@email.com', 'password');

INSERT INTO auction (f_key_consumer_id, auction_start, title, junk_type, volume, container_type, container_number, start_date, end_date, address, note) 
VALUES (1, '01/01/2022', 'junk from previous owners', 'scrap metal', 3, '5m', 1, '31/01/2022', '07/02/2022', '100 Santaclouse Crescent, S55 5SS, London', 'leave container on the street');
INSERT INTO auction (f_key_consumer_id, auction_start, title, junk_type, volume, container_type, container_number, start_date, end_date, address, note) VALUES (1, '07/01/2022', 'junk from kitchen renewal', 'wood', 5, '5m', 1, '08/01/2022', '31/01/2022', '1 Example Street, A00 0AA, London', '');
INSERT INTO auction (f_key_consumer_id, auction_start, title, junk_type, volume, container_type, container_number, start_date, end_date, address, note) VALUES (2, '01/01/2022', 'junk from demolition', 'construction debris', 100, 'truck_24t', 5, '31/03/2022', '07/04/2022', '10 field Line, L99 9LL, Dumper', 'no notes');

INSERT INTO bid (price, f_key_Professional_id, auction_id, date) VALUES (999, 1, 1, '02/01/2022');
INSERT INTO bid (price, f_key_Professional_id, auction_id, date) VALUES (800, 3, 1, '02/01/2022');
INSERT INTO bid (price, f_key_Professional_id, auction_id, date) VALUES (700, 1, 1, '02/01/2022');
INSERT INTO bid (price, f_key_Professional_id, auction_id, date) VALUES (999, 1, 2, '03/01/2022');