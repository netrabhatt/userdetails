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

insert into userdetails values(1, 'Mr.', 'Netra', 'Bhatt', 'Male', '46-48 Beverley Park', 'Kogarah', 'NSW', 2570);

