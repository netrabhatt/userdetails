drop table if exists userdetails;

create table userdetails 
(
	empid int auto_increment primary key,
	title varchar(10),
	first_name varchar(200),
	last_name varchar(200),
	gender varchar(10),
	street varchar(100),
	city varchar(100),
	state varchar(100),
	postcode varchar(20)
);

insert into userdetails values(1, 'Mr.', 'Scott', 'Tiger', 'Male', '121 Beverley Park', 'Kogarah', 'NSW', 2570);
insert into userdetails values(2, 'Ms.', 'Alyse', 'Shanks', 'Female', '122 Beverley Park', 'Kogarah', 'NSW', 2571);
insert into userdetails values(3, 'Mr.', 'Harriett', 'Zingaro', 'Male', '123 Beverley Park', 'Kogarah', 'NSW', 2572);
insert into userdetails values(4, 'Mrs.', 'Vanesa', 'Lindblom', 'Female', '124 Beverley Park', 'Kogarah', 'NSW', 2573);
insert into userdetails values(5, 'Mr.', 'Harold', 'Eckler', 'Male', '125 Beverley Park', 'Kogarah', 'NSW', 2574);
insert into userdetails values(6, 'Mrs.', 'Cristen', 'Lee', 'Female', '126 Beverley Park', 'Kogarah', 'NSW', 2575);
insert into userdetails values(7, 'Ms.', 'Jacquelyn', 'Messing', 'Female', '127 Beverley Park', 'Kogarah', 'NSW', 2576);
insert into userdetails values(8, 'Mr.', 'Estell', 'Filmore', 'Male', '128 Beverley Park', 'Kogarah', 'NSW', 2577);
insert into userdetails values(9, 'Mrs.', 'Sherron', 'Cone', 'Female', '129 Beverley Park', 'Kogarah', 'NSW', 2578);
insert into userdetails values(10, 'Ms.', 'Lucina', 'Akin', 'Female', '130 Beverley Park', 'Kogarah', 'NSW', 2579);
