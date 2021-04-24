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
  manager_id int not null references person(id),
  employee_id int not null references person(id),
  price money,
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

insert into reimbursement (manager_id, employee_id, price, resolved, approved)
values (
  (select id from person where email = 'janedawn@vu.net'),
  (select id from person where email = 'johndoe@vu.net'),
  500, false, false
);
