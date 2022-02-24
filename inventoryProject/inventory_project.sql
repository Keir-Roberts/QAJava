create database inventory;
use inventory;
create table customer(
ID int PRIMARY KEY auto_increment,
firstName VarChar(15) not null,
lastName varchar(15) not null,
email varchar(320),
postcode VarChar(10),
house VarChar(20));

create table item(
ID int PRIMARY KEY auto_increment,
productName VarChar(20) not null,
price  decimal(10,2) not null,
stock bigint not null default 0);

create table orders(
ID int PRIMARY KEY auto_increment,
customerID int not null,
orderDate DATE,
status varChar(10),
FOREIGN KEY (customerID) REFERENCES customer(ID)
);
create table basket(
ID int PRIMARY KEY auto_increment,
orderID int not null,
itemID int not null,
foreign key (orderID) REFERENCES orders(ID),
foreign key (itemID) REFERENCES item(ID)
);
