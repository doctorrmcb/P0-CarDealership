-- Creating test tables.
create table test.accounts (
	accountusername varchar(40),
	password varchar(40),
	accountstatus varchar(10),
	constraint PK_accounts primary key (accountusername)
);

create table test.cars (
	ownerusername varchar(40),
	vin varchar(17),
	constraint PK_cars primary key (vin)
);

create table test.offers (
	offerusername varchar(40),
	vin varchar(17),
	price numeric(10,2),
	durationmonths numeric (4,0),
	offerstatus varchar(10),
	constraint PK_offers primary key (offerusername, vin)
);

create table test.payments (
	paymentusername varchar(40),
	vin varchar(17),
	paymenttime timestamp,
	amount numeric(10,2),
	constraint PK_payments primary key (paymenttime, vin)
);

-- Creating development tables.
create table dev.accounts (
	accountusername varchar(40),
	password varchar(40),
	accountstatus varchar(10),
	constraint PK_accounts primary key (accountusername)
);

create table dev.cars (
	ownerusername varchar(40),
	vin varchar(17),
	constraint PK_cars primary key (vin)
);

create table dev.offers (
	offerusername varchar(40),
	vin varchar(17),
	price numeric(10,2),
	durationmonths numeric (4,0),
	offerstatus varchar(10),
	constraint PK_offers primary key (offerusername, vin)
);

create table dev.payments (
	paymentusername varchar(40),
	vin varchar(17),
	paymenttime timestamp,
	amount numeric(10,2),
	constraint PK_payments primary key (paymenttime, vin)
);

-- This file assumes that there is already a production database set up.

-- Drop statements for helping debug table creation.
--drop table test.accounts;
