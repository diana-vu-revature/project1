\c postgres

create table user_role(
  id serial primary key,
  role_name varchar(255) not null    
);

create table users(
  id serial primary key,
  FOREIGN KEY(role_id) 
    REFERENCES role(id),
  fname text not null, 
  surname text not null, 
  email text not null, 
  pword text not null
);

create table reimbursement(
  id serial primary key,
  manager_id varchar(255) not null,
  employee_id varchar(255) not null,
  price int,
  resolved boolean default false not null,
  FOREIGN KEY(employee_id) 
    REFERENCES users(id),
  FOREIGN KEY(manager_id) 
    REFERENCES user(id)
);

insert into user_role(
  role_name values (employee)
);
insert into user_role(
  role_name values (manager)
);

insert into users(
  roleid, 
  fname, 
  surname, 
  email,
  pword) 
  values (
    select id from user_role where role_name = 'employee', 
    John, 
    Doe, 
    johndoe@vu.net, 
    p4ssw0rd
);
