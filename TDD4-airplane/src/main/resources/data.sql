INSERT INTO Airline(name) VALUES('항공사1');
INSERT INTO Airline(name) VALUES('항공사2');
INSERT INTO Airline(name) VALUES('항공사3');
INSERT INTO Airline(name) VALUES('항공사4');
INSERT INTO Airline(name) VALUES('항공사5');

INSERT INTO User(airline_id, email, password, mileage) VALUES(1L, 'email', 'password', 0);
INSERT INTO User(airline_id, email, password, mileage) VALUES(2L, 'email2', 'password2', 0);
INSERT INTO User(airline_id, email, password, mileage) VALUES(3L, 'email3', 'password3', 0);

INSERT INTO Schedule(id, airline_id, arrivals, departures, arrival_time, departure_time, capacity) VALUES (null, 1L, '출발지1', '도착지1', '2021-03-21', '2021-03-27', 100);
INSERT INTO Schedule(id, airline_id, arrivals, departures, arrival_time, departure_time, capacity) VALUES (null, 1L, '출발지2', '도착지2', '2021-03-22', '2021-03-27', 100);
INSERT INTO Schedule(id, airline_id, arrivals, departures, arrival_time, departure_time, capacity) VALUES (null, 2L, '출발지3', '도착지3', '2021-03-23', '2021-03-27', 200);
INSERT INTO Schedule(id, airline_id, arrivals, departures, arrival_time, departure_time, capacity) VALUES (null, 2L, '출발지4', '도착지4', '2021-03-24', '2021-03-27', 200);
INSERT INTO Schedule(id, airline_id, arrivals, departures, arrival_time, departure_time, capacity) VALUES (null, 2L, '출발지5', '도착지5', '2021-03-25', '2021-03-27', 300);
INSERT INTO Schedule(id, airline_id, arrivals, departures, arrival_time, departure_time, capacity) VALUES (100L, 2L, '출발지5', '도착지5', '2021-03-25', '2021-03-27', 300);


INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(null, 1, 1, 'FIRST', 'AVAILABLE');
INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(null, 2, 1, 'FIRST', 'AVAILABLE');
INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(null, 3, 1, 'FIRST', 'AVAILABLE');
INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(null, 4, 1, 'FIRST', 'AVAILABLE');
INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(null, 1, 1, 'BUSINESS', 'AVAILABLE');
INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(null, 2, 1, 'BUSINESS', 'AVAILABLE');
INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(null, 1, 1, 'ECONOMY', 'AVAILABLE');
INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(null, 2, 1, 'ECONOMY', 'AVAILABLE');
INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(9, 1, 2, 'FIRST', 'AVAILABLE');
INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(null, 1, 2, 'BUSINESS', 'AVAILABLE');
INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(null, 1, 2, 'ECONOMY', 'AVAILABLE');
INSERT INTO Seat(id, seat_number, schedule_id, class_seat, status) VALUES(null, 2, 2, 'ECONOMY', 'AVAILABLE');


INSERT INTO Reservation(id, schedule_id, user_id, seat_id, price, status) VALUES(null, 1L, 1L, 1L, 1000000, 'RESERVED');
INSERT INTO Reservation(id, schedule_id, user_id, seat_id, price, status) VALUES(null, 2L, 1L, 2L, 2000000, 'RESERVED');
INSERT INTO Reservation(id, schedule_id, user_id, seat_id, price, status) VALUES(null, 3L, 2L, 3L, 1000000, 'RESERVED');
INSERT INTO Reservation(id, schedule_id, user_id, seat_id, price, status) VALUES(null, 4L, 2L, 4L, 3000000, 'RESERVED');
INSERT INTO Reservation(id, schedule_id, user_id, seat_id, price, status) VALUES(null, 1L, 3L, 5L, 4000000, 'RESERVED');
INSERT INTO Reservation(id, schedule_id, user_id, seat_id, price, status) VALUES(null, 2L, 3L, 6L, 5000000, 'RESERVED');
INSERT INTO Reservation(id, schedule_id, user_id, seat_id, price, status) VALUES(null, 1L, 3L, 7L, 10000, 'RESERVED');



