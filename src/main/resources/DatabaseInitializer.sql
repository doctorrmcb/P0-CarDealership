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


ALTER TABLE test.cars add CONSTRAINT FK_AccountUsername
    FOREIGN KEY (ownerusername) REFERENCES test.accounts (accountusername) ON DELETE cascade ON UPDATE cascade;

alter table test.payments add constraint FK_CarVin
	foreign key (vin) references test.cars (vin) on delete cascade on update cascade;
	
alter table test.payments add constraint FK_AccountUsername
	foreign key (paymentusername) references test.accounts (accountusername) on delete cascade on update cascade;

alter table test.offers add constraint FK_CarVin
	foreign key (vin) references test.cars (vin) on delete cascade on update cascade;

alter table test.offers add constraint FK_AccountUsername
	foreign key (offerusername) references test.accounts (accountusername) on delete cascade on update cascade;

ALTER TABLE dev.cars ADD CONSTRAINT FK_AccountUsername
    FOREIGN KEY (ownerusername) REFERENCES dev.accounts (accountusername) ON DELETE cascade ON UPDATE cascade;

alter table dev.payments add constraint FK_CarVin
	foreign key (vin) references dev.cars (vin) on delete cascade on update cascade;

alter table dev.payments add constraint FK_AccountUsername
	foreign key (paymentusername) references dev.accounts (accountusername) on delete cascade on update cascade;

alter table dev.offers add constraint FK_CarVin
	foreign key (vin) references dev.cars (vin) on delete cascade on update cascade;
	
alter table dev.offers add constraint FK_AccountUsername
	foreign key (offerusername) references dev.accounts (accountusername) on delete cascade on update cascade;

--ALTER TABLE test.offers DROP CONSTRAINT fk_carvin;
--ALTER TABLE test.offers DROP CONSTRAINT fk_accountusername;
--ALTER TABLE test.payments DROP CONSTRAINT fk_carvin;
--ALTER TABLE test.payments DROP CONSTRAINT fk_accountusername;
--ALTER TABLE test.cars DROP CONSTRAINT FK_AccountUsername;

--ALTER TABLE dev.offers DROP CONSTRAINT fk_carvin;
--ALTER TABLE dev.offers DROP CONSTRAINT fk_accountusername;
--ALTER TABLE dev.payments DROP CONSTRAINT fk_carvin;
--ALTER TABLE dev.payments DROP CONSTRAINT fk_accountusername;
--ALTER TABLE dev.cars DROP CONSTRAINT FK_AccountUsername;

create or replace
	procedure delete_test_data() as $deletetestproc$	
	begin
		delete from test.accounts where accountusername = 'testUser';
		delete from test.accounts where accountusername = 'TestUser';
		delete from test.accounts where accountusername = 'TestCust';
		delete from test.accounts where accountusername = 'goes.';
		delete from test.accounts where accountusername = 'Robert'');';
		delete from test.cars where vin = 'Anything';
		delete from test.cars where vin = 'testVin';
		commit;
	end;
$deletetestproc$ language plpgsql;

call delete_test_data();
