\c postgres

create table users(
    id serial primary key, 
    role_id text not null, 
    fname text not null, 
    surname text not null, 
    email text not null, 
    pword text not null
);

insert into users(
    roleid, 
    fname, 
    surname, 
    email,) 
    values (
        employee, 
        John, 
        Doe, 
        johndoe@vu.net, 
        p4ssw0rd
);

create table reimbursement(
    id serial primary key,
    manager_id varchar(255) not null,
    employee_id varchar(255) not null,
    price int,
    FOREIGN KEY(employee_id) 
      REFERENCES users(id),
    FOREIGN KEY(manager_id) 
      REFERENCES user(id)
);

create table "role"(
    id serial primary key,
    role_name varchar(255) not null    
);