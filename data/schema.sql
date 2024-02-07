create table users
(
    id                 int generated always as identity,
    email              varchar(255) unique not null,
    password           varchar(255)        not null,
    role               varchar(255)        not null,
    primary key (id)
);