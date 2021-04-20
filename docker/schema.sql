\c postgres

create table employees(id serial primary key, fname text not null, surname text not null, email text not null, pword text not null, reimbursement int not null);

insert into employees(fname, surname, email, reimbursement) values (John, Doe, johndoe@vu.net, p4ssw0rd, 0);