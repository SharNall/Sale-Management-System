use saleManagement;
create table Customers (
	CustomersID int auto_increment primary key,
    name varchar(32),
    phone varchar(32),
    address varchar(255)


);
insert into Customers (name,phone,address) values ("ahmad","059903411","al-ram,jerusalem,palestine");
insert into Customers (name,phone,address) values ("mohammad","056243411","ein-sara,hebraw,palestine");
insert into Customers (name,phone,address) values ("khalid","056923341","betonia,ramallah,palestine");
insert into Customers (name,phone,address) values ("ali","059223431","al-birah,ramallah,palestine");
select * from Customers;
create table Worker (
	workerID int auto_increment primary key,
    name varchar(32),
    phone varchar(32),
    salary real,
    workertype varchar(10)
);
insert into Worker (name,phone,salary,workertype) values ("ahmad","059903411",6000,"maneger");
insert into Worker (name,phone,salary,workertype) values ("mohammad","056243411",4000,"emplyee");
insert into Worker (name,phone,salary,workertype) values ("khalid","056923341",4000,"emplyee");
insert into Worker (name,phone,salary,workertype) values ("ali","059223431",4000,"emplyee");
select * from Worker;
create table Items (
	ItemsID int auto_increment primary key,
    BarcodeNum int,
    name varchar(32),
    ExpirationDate date,
    sellingPrice float,
	buyingPrice float,
    StoredQuantity int(32),
    category varchar(255)


);
insert into Items (name,BarcodeNum,ExpirationDate,sellingPrice,buyingPrice,StoredQuantity,category) values ("bread",3254326,'2024-3-1',10,8.3,200,"Starches");
insert into Items (name,BarcodeNum,ExpirationDate,sellingPrice,buyingPrice,StoredQuantity,category) values ("XL",24364326,'2024-4-2',4,3.3,130,"drinks");
insert into Items (name,BarcodeNum,ExpirationDate,sellingPrice,buyingPrice,StoredQuantity,category) values ("Milk",2346423,'2024-2-3',14,12.5,40,"Dairy");
insert into Items (name,BarcodeNum,ExpirationDate,sellingPrice,buyingPrice,StoredQuantity,category) values ("Tuna",12351325,'2024-6-18',6,5.5,120,"meat");
select * from Items;
create table Orders (
	OrdersID int auto_increment primary key,
    Date date,
    totalprice float,
    Quantity int(32),
    MerchantsID int(10),
    FOREIGN KEY (MerchantsID)
		        	      	REFERENCES 	Merchants (MerchantsID)


);
insert into Orders (Date,totalprice,Quantity,MerchantsID) values ('2024-3-1',300,200,1);
insert into Orders (Date,totalprice,Quantity,MerchantsID) values ('2024-4-2',242,130,2);
insert into Orders (Date,totalprice,Quantity,MerchantsID) values ('2024-2-3',325,40,3);
insert into Orders (Date,totalprice,Quantity,MerchantsID) values ('2024-6-18',125,120,4);
create table Merchants (
	MerchantsID int auto_increment primary key,
     name varchar(32),
    total_debts float,
    phone varchar(32),
    address varchar(255)

);
insert into Merchants (name,total_debts,phone,address) values ("junaidi",25000,"059942532","al-ram,jerusalem,palestine");
insert into Merchants (name,total_debts,phone,address) values ("jubrini",12500,"059957232","ein-sara,hebraw,palestine");
insert into Merchants (name,total_debts,phone,address) values ("subul",2500,"059975243","betonia,ramallah,palestine");
insert into Merchants (name,total_debts,phone,address) values ("alkhumaini",3700,"059918432","al-birah,ramallah,palestine");
select * from Merchants;
create table items_Orders(
	ItemsID int,
    OrdersID int,
    FOREIGN KEY (OrdersID)
		        	      	REFERENCES 	Orders (OrdersID),
	FOREIGN KEY (ItemsID)
		        	      	REFERENCES 	items (ItemsID)


);
create table items_Customers(
	ItemsID int,
    CustomersID int,
    FOREIGN KEY (CustomersID)
		        	      	REFERENCES 	Customers (CustomersID),
	FOREIGN KEY (ItemsID)
		        	      	REFERENCES 	items (ItemsID)


);
create table Returned_items(
returnedID int auto_increment primary key,
	returnedDate date,
    itemsID int,
    MerchantsID int,
    FOREIGN KEY (MerchantsID)
		        	      	REFERENCES 	Merchants (MerchantsID),
	FOREIGN KEY (ItemsID)
		        	      	REFERENCES 	items (ItemsID)


);
create table cashflow (
	DayID int auto_increment primary key,
    Date date,
    debtOnUS int,
    paidDebts int(32),
    totalSalaries int(10),
     totalCash int(32),
    totalIncome int(10)


);

create table cashflow_Merchants(
	paid_debts float,
    Date  date,
    MerchantsID int,
    FOREIGN KEY (MerchantsID)
		        	      	REFERENCES 	Merchants (MerchantsID),
	FOREIGN KEY (Date)
		        	      	REFERENCES 	cashflow (Date)


);
create table Cashflow_Worker(
	paidSalarys float,
    Date  date,
    WorkerID int,
    FOREIGN KEY (WorkerID)
		        	      	REFERENCES 	Worker (WorkerID),
	FOREIGN KEY (Date)
		        	      	REFERENCES 	cashflow (Date)


);
