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

--ALTER TABLE Album ADD CONSTRAINT FK_AlbumArtistId
--    FOREIGN KEY (ArtistId) REFERENCES Artist (ArtistId) ON DELETE NO ACTION ON UPDATE NO ACTION;


ALTER TABLE test.cars ADD CONSTRAINT FK_AccountUsername
    FOREIGN KEY (ownerusername) REFERENCES test.accounts (accountusername) ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table test.payments add constraint FK_CarVin
	foreign key (vin) references test.cars (vin) on delete no action on update no action;
	
alter table test.payments add constraint FK_AccountUsername
	foreign key (paymentusername) references test.accounts (accountusername) on delete no action on update no action;

alter table test.offers add constraint FK_CarVin
	foreign key (vin) references test.cars (vin) on delete no action on update no action;

alter table test.offers add constraint FK_AccountUsername
	foreign key (offerusername) references test.accounts (accountusername) on delete no action on update no action;


ALTER TABLE dev.cars ADD CONSTRAINT FK_AccountUsername
    FOREIGN KEY (ownerusername) REFERENCES dev.accounts (accountusername) ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table dev.payments add constraint FK_CarVin
	foreign key (vin) references dev.cars (vin) on delete no action on update no action;

alter table dev.payments add constraint FK_AccountUsername
	foreign key (paymentusername) references dev.accounts (accountusername) on delete no action on update no action;

alter table dev.offers add constraint FK_CarVin
	foreign key (vin) references dev.cars (vin) on delete no action on update no action;
	
alter table dev.offers add constraint FK_AccountUsername
	foreign key (offerusername) references dev.accounts (accountusername) on delete no action on update no action;
