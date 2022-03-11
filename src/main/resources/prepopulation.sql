INSERT INTO consumer (address, phone, email, full_name, password) VALUES ('10 First street S11 1SS London', '1111-111-111', 'First@email.com', 'Firstofer Firstof', 'password1');
INSERT INTO consumer (address, phone, email, full_name, password) VALUES ('20 Second street S22 2SS London', '2222-222-222', 'Second@email.com', 'Secendorf Secend', 'password2');
INSERT INTO consumer (address, phone, email, full_name, password) VALUES ('30 Third street S33 3SS London', '3333-333-333', 'Third@email.com', 'Thirdofer Third', 'password3');
INSERT INTO consumer (email, password) VALUES ('email@email.com', 'password');


INSERT INTO professional (firm, address, phone, email, full_name, password, license_no) VALUES ('Firm3', '30 Third street S33 3SS London', '3333-333-333', 'Third@email.com', 'Thirdofer Third', 'password3', 'lisdbflbc');
INSERT INTO professional (firm, address, phone, email, full_name, password, license_no) VALUES ('Firm3', '20 Second street S22 2SS London', '2222-222-222', 'Second@email.com', 'Secendorf Secend', 'password2', 'lijbdfkljq');
INSERT INTO professional (firm, address, phone, email, full_name, password, license_no) VALUES ('Firm3', '30 Third street S33 3SS London', '3333-333-333', 'Third@email.com', 'Thirdofer Third', 'password3', 'xqasdliuq');
INSERT INTO professional (email, password) VALUES ('email@email.com', 'password');

INSERT INTO auction (f_key_consumer_id) VALUES (1);
INSERT INTO auction (f_key_consumer_id) VALUES (1);
INSERT INTO auction (f_key_consumer_id) VALUES (2);

INSERT INTO bid (price, f_key_Professional_id, auction_id) VALUES (999, 1, 1);
INSERT INTO bid (price, f_key_Professional_id, auction_id) VALUES (800, 3, 1);
INSERT INTO bid (price, f_key_Professional_id, auction_id) VALUES (700, 1, 1);
INSERT INTO bid (price, f_key_Professional_id, auction_id) VALUES (999, 1, 2);