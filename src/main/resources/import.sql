INSERT INTO user (id,login) VALUES (1,'Cade');
INSERT INTO user (id,login) VALUES (2,'Troy');
INSERT INTO user (id,login) VALUES (3,'Forrest');
INSERT INTO user (id,login) VALUES (4,'Zeus');
INSERT INTO user (id,login) VALUES (5,'Uriel');
INSERT INTO user (id,login) VALUES (6,'Edan');

INSERT INTO service (id,name,price) VALUES (1,'breakfast','98');
INSERT INTO service (id,name,price) VALUES (2,'smiling','50');
INSERT INTO service (id,name,price) VALUES (3,'cleaning','10');

INSERT INTO room (id,number,category,price) VALUES (1,1,'VIP','199');
INSERT INTO room (id,number,category,price) VALUES (2,3,'ORDINARY','597.4');
INSERT INTO room (id,number,category,price) VALUES (3,5,'VIP','639');
INSERT INTO room (id,number,category,price) VALUES (4,7,'VIP','821.3');
INSERT INTO room (id,number,category,price) VALUES (5,9,'VIP','123');
INSERT INTO room (id,number,category,price) VALUES (6,11,'ORDINARY','840.1');
INSERT INTO room (id,number,category,price) VALUES (7,13,'VIP','583');
INSERT INTO room (id,number,category,price) VALUES (8,15,'ORDINARY','864.5');
INSERT INTO room (id,number,category,price) VALUES (9,17,'VIP','787');
INSERT INTO room (id,number,category,price) VALUES (10,19,'VIP','237');

INSERT INTO room_service(room_id, service_id) VALUES ( 1,1);
INSERT INTO room_service(room_id, service_id) VALUES ( 1,2);
INSERT INTO room_service(room_id, service_id) VALUES ( 3,1);
INSERT INTO room_service(room_id, service_id) VALUES ( 7,1);
INSERT INTO room_service(room_id, service_id) VALUES ( 7,2);
INSERT INTO room_service(room_id, service_id) VALUES ( 7,3);
INSERT INTO room_service(room_id, service_id) VALUES ( 9,1);

INSERT INTO booking (id,user_id,room_id,start_date,finish_date) VALUES (1,'5','10','2018-04-25','2018-04-26');
INSERT INTO booking (id,user_id,room_id,start_date,finish_date) VALUES (2,'4','1','2018-04-23','2018-04-28');
INSERT INTO booking (id,user_id,room_id,start_date,finish_date) VALUES (3,'5','3','2018-04-21','2018-04-26');
INSERT INTO booking (id,user_id,room_id,start_date,finish_date) VALUES (4,'1','4','2018-04-23','2018-04-26');
INSERT INTO booking (id,user_id,room_id,start_date,finish_date) VALUES (5,'6','6','2018-04-22','2018-04-23');
INSERT INTO booking (id,user_id,room_id,start_date,finish_date) VALUES (6,'2','6','2018-04-24','2018-04-30');
INSERT INTO booking (id,user_id,room_id,start_date,finish_date) VALUES (7,'2','4','2018-04-29','2018-04-30');
INSERT INTO booking (id,user_id,room_id,start_date,finish_date) VALUES (8,'4','1','2018-04-28','2018-04-29');
INSERT INTO booking (id,user_id,room_id,start_date,finish_date) VALUES (9,'2','7','2018-04-24','2018-04-30');