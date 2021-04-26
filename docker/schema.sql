\c postgres

create table user_role (
  id int generated always as identity primary key,
  role_name varchar(255) not null unique
);

insert into user_role (role_name) values ('employee');
insert into user_role (role_name) values ('manager');

create table person (
  id int generated always as identity primary key,
  role_id int not null references user_role(id),
  fname text not null, 
  surname text not null, 
  email text not null, 
  pword text not null
);

create table reimbursement(
  id int generated always as identity primary key,
  reim_name varchar(255) not null,
  manager_id int references person(id),
  employee_id int not null references person(id),
  price double precision not null,
  resolved boolean default false not null,
  approved boolean
);

insert into person (role_id, fname, surname, email, pword) 
values (
  (select id from user_role where role_name = 'employee'), 
  'John', 'Doe', 'johndoe@vu.net', 'p4ssw0rd'
);

insert into person (role_id, fname, surname, email, pword) 
values (
  (select id from user_role where role_name = 'manager'), 
  'Jane', 'Dawn', 'janedawn@vu.net', 'hello'
);

insert into person (role_id, fname, surname, email, pword) 
values (
  (select id from user_role where role_name = 'manager'), 
  'Diana', 'Vu', 'dianavu@vu.net', 'h3ll0'
);

insert into reimbursement (reim_name, manager_id, employee_id, price, resolved, approved)
values (
  'business trip',
  (select id from person where email = 'janedawn@vu.net'),
  (select id from person where email = 'johndoe@vu.net'),
  500, true, true
);
