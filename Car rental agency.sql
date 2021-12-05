REM   Script: Session 02
REM   Car rental agency database

CREATE TABLE categories( 
    cat_id INT, 
    cat_label VARCHAR(50), 
    cat_desc VARCHAR(250), 
    PRIMARY KEY (cat_id) 
    );

CREATE TABLE cars( 
    VIN VARCHAR(20), 
    car_desc VARCHAR(250), 
    color VARCHAR(45), 
    brand VARCHAR(45), 
    model VARCHAR(45), 
    cat_id INT, 
    purch_date DATE, 
    PRIMARY key(VIN), 
    FOREIGN KEY (cat_id) REFERENCES categories(cat_id) 
    );

CREATE TABLE locations( 
    loc_id INT, 
    street VARCHAR(45), 
    street_no VARCHAR(10), 
    city VARCHAR(45), 
    state1 VARCHAR(45), 
    country VARCHAR(45), 
    PRIMARY key (loc_id) 
    );

CREATE TABLE phones( 
    phone_id INT, 
    phone_no VARCHAR(45), 
    loc_id INT, 
    PRIMARY KEY(phone_id), 
    FOREIGN KEY(loc_id) REFERENCES locations(loc_id) 
    );

CREATE table customers( 
    id INT, 
    first_name VARCHAR(45), 
    last_name VARCHAR(45), 
    email VARCHAR(45), 
    ssn VARCHAR(11), 
    mobile VARCHAR(15), 
    state_abbrev VARCHAR(45), 
    state_name VARCHAR(45), 
    country VARCHAR(45), 
    PRIMARY key(id) 
    );

CREATE TABLE reservations( 
    reservation_id INT, 
    VIN VARCHAR(20), 
    cust_id int, 
    amount DECIMAL, 
    p_loc INT, 
    p_date DATE, 
    r_loc int , 
    r_date DATE, 
    PRIMARY KEY (reservation_id), 
    FOREIGN key(VIN) REFERENCES cars(VIN), 
    FOREIGN KEY(cust_id) REFERENCES customers(id), 
    FOREIGN KEY(p_loc) REFERENCES locations(loc_id), 
    FOREIGN KEY(r_loc) REFERENCES locations(loc_id) 
    );

iNSERT INTO categories VALUES(101,'Compact', 'Sedan-type car with 5 doors');

iNSERT INTO categories VALUES(102,'Convertible', 'The roof of the car is retractable, hard-top or soft-top');

iNSERT INTO categories VALUES(103,'Jeep', '4X4, tall vehicle, usually suitable for off-road conditions');

INSERT INTO cars VALUES('ZTY4567', 'Convertible with hardtop leather seats and CD player', 'Silver', 'Mercedes-Benz', 'SLK200',101, '08-feb-2021');

INSERT INTO cars VALUES('ATB2646', 'Professional and good-looking full-extra', 'Blue', 'BMW', '160i', 102,'11-aug-2021');

INSERT INTO cars VALUES ('IKP3998', 'Stylish eye-catching with GPS','Red','Suzuki', 'Swift',103,'16-sep-2021');

INSERT INTO locations VALUES(1,'Laxmi Road','19121','Pune','Maharashtra','India');

INSERT INTO locations VALUES(2,'Dadabhai Naoroji Road','19122','Mumbai','Maharashtra','India');

INSERT INTO locations VALUES(3,'Sultan Bazar','19123','hyderabad','Telangana','India');

INSERT into customers values(18,'Pratibha','Antre','pratibharantre@gmail.com','721-07-4426','8080176184','MH','Maharashtra','India');

INSERT into customers values(09,'Divya','Sale','divyasale1310@gmail.com','567-07-4426','777587851','UK','Uttarankhand','India');

INSERT into customers values(25,'Rohan','Jadhav','rohanj@gmail.com','721-07-4676','9767855714','GJ','Gujarat','India');

INSERT INTO phones VALUES(91,8080176184,1);

INSERT INTO phones VALUES(92,7775878531,2);

INSERT INTO phones VALUES(93,8657854372,3);

INSERT INTO reservations VALUES(111,'ZTY4567',18,132.23,1,'2 JAN 2021',1,'5 FEB 2021');

INSERT INTO reservations VALUES(222,'ATB2646',09,115.68,2,'10 MAR 2021',2,'12 APR 2021');

select * from categories;

select * from categories;

select * from cars;

select * from locations;

select * from phones;

select * from customers;

select * from reservations;

Alter table customers  
add age int;

select * from customers;

Alter table customers 
drop column age;

select * from customers;

update customers 
set country='japan' 
where first_name='Divya';

select * from customers;

