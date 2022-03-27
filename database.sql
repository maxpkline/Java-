-- use mkline;

drop table if exists AccountAsset;
drop table if exists Account;
drop table if exists Email;
drop table if exists Address;
drop table if exists Persons;
drop table if exists Asset;

create table Asset (
assetId int primary key not null auto_increment,
assetName varchar(255) not null,
assetPrice double not null, -- holds the price of the specific asset
cryptoExchangeFeeRate double,
stockSymbol varchar(255)
);

create table Persons (
personId int primary key not null auto_increment,
lastName varchar(20) not null,
firstName varchar(20) not null
);

create table Address (
addressId int primary key not null auto_increment,
personId int not null,
street varchar(200) not null,
city varchar(30) not null,
state varchar(30) not null,
zipcode varchar(30) not null,
country varchar(30) not null,
foreign key (personId) references Persons(personId)
);

create table Email (
emailId int primary key not null auto_increment,
personId int not null,
email varchar(255) not null,
foreign key (personId) references Persons(personId)
);

create table Account (
accountId int primary key not null auto_increment,
accountType varchar(255) not null,
personId int not null,
managerId int not null,
beneficiaryId int,
foreign key (personId) references Persons(personId),
foreign key (managerId) references Persons(personId),
foreign key (beneficiaryId) references Persons(personId)
);

create table AccountAsset (
accountAssetId int primary key not null auto_increment,
accountId int not null,
assetId int not null,
assetPurchaseDate varchar(255) not null, 
assetCost double not null,  -- holds property cost, original crypto exchange rate, stock purchase share price, and the stock option share/strike price
assetQuantity double not null,  -- holds the number of shares, or the amount of coins owned, could be used to detect duplicate properties
dividendTotal double,  -- just to hold the amount of dividends earned on a stock (kept seperate for consistency)
optionSharePremium double,  -- just for an option, this holds the share premium price
optionStrikeDate varchar(255),  -- just for an option, holds the strike date 
foreign key (accountId) references Account(accountId),
foreign key (assetId) references Asset(assetId)
);


-- Inserting test data into the Persons table
insert into Persons(lastName, firstName) values 
("Klein", "Calvin"),
("Smith", "Joe"),
("Durant", "Kevin"),
("Stevens", "Greeney"),
("Madison", "Billy"),
("Sandy", "Trevor"),
("Banks", "Tyrone"),
("Jacobs", "Shaun"),
("Dor", "Griffin");

-- Inserting test data into the Address table
insert into Address(personId, street, city, state, zipcode, country) values 
("1", "4523 N 167th Ave", "Omaha", "NE", "68116", "USA"),
("2", "107 W F St", "Lincoln", "NE", "68508", "USA"),
("3", "3923 N 165 St", "Lincoln", "NE", "68508", "USA"),
("4", "123 West Street", "New York City", "NY", "10001", "USA"),
("5", "678 S 14 Ave", "Omaha", "NE", "68154", "USA"),
("6", "5678 Wayners Way", "Lincoln", "NE", "68112", "USA"),
("7", "906 N South Circle", "Austin", "TX", "73301", "USA"),
("8", "7801 String Array Road", "Chicago", "IL", "60106", "USA"),
("9", "8907 Yutan Street", "Baton Rouge", "LA", "70801", "USA");

-- Inserting test data into the Email table
insert into Email(personId, email) values 
("1", "CalvinK463@yahoo.com"),
("1", "Cklein@hmail.com"),
("2", "SmithersonJoe@gmail.net"),
("4", "Greenyboy12@gmail.net"),
("5", "BillMadisonGM@kellogs.com"),
("5",  "BillyMads27@wow.com"),
("5", "Bmads@123.com"),
("6", "Tsandy@gmail.com"),
("6", "TrevorS123@gmail.org"),
("8", "shaunjacobs@12enums.com"),
("9", "griffindor@12enums.com"),
("9", "uknowME@yahoo.google");

-- Inserting test data into the Asset table
insert into Asset(assetName, assetPrice, cryptoExchangeFeeRate, stockSymbol) values
("Ethereum", 3019.83, 7.5, null), 
("Tether", 1, 0, null), 
("Bitcoin", 42858, 2.5, null), 
("Chainbing", 21.89, 2.7, null), 
("CoinStorey", 0.0002115, 0.174, null), 
("Lake House", 435000, null, null), 
("Infiniti xQ30", 12500, null, null), 
("Gold Toilet", 12.35, null, null), 
("James Webb Telescope", 12000000, null, null), 
("Amazon Warehouse", 1000000, null, null), 
("Tesla", 904.55, null, "TSLA"), 
("Microsoft", 302.38, null, "MSFT"), 
("Penn Gaming", 50.13, null, "PENN"), 
("General Motors", 50.34, null, "GM"), 
("Wells Fargo", 59.05, null, "WFC");

-- Inserting test data into the Account table
insert into Account(accountType, personId, managerId, beneficiaryId) values 
("Pro", 7, 1, 1),
("Pro", 1, 7, 2),
("Noob", 2, 1, 3),
("Noob", 3, 6, 7),
("Pro", 5, 6, 4);

-- Inserting test data into the AccountAsset table
insert into AccountAsset(accountId, assetId, assetPurchaseDate, assetCost, assetQuantity, dividendTotal, optionSharePremium, optionStrikeDate) values 
(1, 1, "2020-03-04", 345.67, 123.5, null, null, null),
(1, 2, "2021-02-11", 10.57, 1, null, null, null),
(2, 3, "2020-04-20", 2347.89, 1000, null, null, null),
(2, 4, "2021-02-13", 20.34, 12, null, null, null),
(2, 5, "2022-09-01", 0.00007891, 19000, null, null, null),
(3, 6, "2009-02-10", 95000, 0, null, null, null),
(3, 6, "2011-12-23", 95000, 0, null, null, null),
(3, 7, "2020-09-29", 25000, 0, null, null, null),
(4, 8, "2014-07-18", 10000000, 0, null, null, null),
(4, 9, "2020-09-07", 100000000, 0, null, null, null),
(4, 10, "2021-01-01", 525000, 0, null, null, null),
(2, 11, "2021-01-01", 325.98, 1255.23, 125354.23, null, null),
(2, 12, "2021-01-01", 301.12, 100000, 0.0, 1.25, "2022-01-01"),
(5, 13, "2021-01-01", 2.34, 56, 0.0, 0.25, "2022-01-01"),
(5, 14, "2021-01-01", 45.12, 102, 100034.23, 1.00, "2020-01-01"),
(5, 15, "2021-01-01", 12.34, 340, 0.0, 5.25, "2020-01-01");
