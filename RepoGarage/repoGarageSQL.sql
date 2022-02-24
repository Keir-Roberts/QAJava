use garage;

create table car(
ID int PRIMARY KEY auto_increment,
wheels int,
colour varchar(20),
maker varchar(20),
horsePower int
);

create table truck(
ID int PRIMARY KEY auto_increment,
wheels int,
colour varchar(20),
capacity int
);
Insert INTO car(wheels, colour, maker, horsePower) values(4, 'Orange', 'Toyota', 300);
Insert INTO truck(wheels, colour, capacity) values (8, 'Red and White', 300);
select * from car;