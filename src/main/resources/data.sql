/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');


insert into manufacturer(id, date_created, deleted, brand_name, warranty_period)
values
(1, now(), false,'BMW',4);

insert into manufacturer(id, date_created, deleted, brand_name, warranty_period)
values
(2, now(), false,'OPEL',2);

insert into manufacturer(id, date_created, deleted, brand_name, warranty_period)
values
(3, now(), false,'MERCEDES',5);

insert into manufacturer(id, date_created, deleted, brand_name, warranty_period)
values
(4, now(), false,'WOLKSVAGEN',2);

insert into car (id, date_created, license_plate, deleted, seat_count, convertible, rating,
engine_type, color, gear_type, model_year, hp, manufacturer_id)
values
(1, now(),
'34DG2222', false, 5, false, 4, 'GAS','RED','AUTOMATIC','2017',1.6, 2
);

insert into car (id, date_created, license_plate, deleted, seat_count, convertible, rating,
engine_type, color, gear_type, model_year, hp, manufacturer_id)
values
(2, now(),
'34AC4576', false, 2, false, 4, 'ELECTRIC', 'BLUE','MANUAL','2018',1.8, 3
);

insert into car (id, date_created, license_plate, deleted, seat_count, convertible, rating,
engine_type, color, gear_type, model_year, hp, manufacturer_id)
values
(3, now(),
'34KHB76', false, 2, false, 4, 'GAS', 'WHITE','AUTOMATIC','2014',1.4, 1
);

insert into car (id, date_created, license_plate, deleted, seat_count, convertible, rating,
engine_type, color, gear_type, model_year, hp, manufacturer_id)
values
(4, now(),
'22AT7532', false, 5, false, 4, 'ELECTRIC', 'RED','MANUAL','2015',1.6, 1
);

insert into car (id, date_created, license_plate, deleted, seat_count, convertible, rating,
engine_type, color, gear_type, model_year, hp, manufacturer_id)
values
(5, now(),
'39AH9365', false, 7, false, 4, 'GAS', 'RED','AUTOMATIC','2015',1.8, 4
);

insert into car (id, date_created, license_plate, deleted, seat_count, convertible, rating,
engine_type, color, gear_type, model_year, hp, manufacturer_id)
values
(6, now(),
'39YH6665', false, 7, false, 4, 'GAS', 'BLACK','AUTOMATIC','2015',1.6, 4
);

insert into car (id, date_created, license_plate, deleted, seat_count, convertible, rating,
engine_type, color, gear_type, model_year, hp, manufacturer_id)
values
(7, now(),
'34AT6523', false, 7, false, 4, 'ELECTRIC', 'RED','AUTOMATIC','2016',1.8, 3
);

insert into car (id, date_created, license_plate, deleted, seat_count, convertible, rating,
engine_type, color, gear_type, model_year, hp, manufacturer_id)
values
(8, now(),
'35YC7615', false, 7, false, 4, 'ELECTRIC', 'WHITE','MANUAL','2018',1.2, 1
);
