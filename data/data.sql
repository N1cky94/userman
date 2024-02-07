insert into users
(email,password,role, account_activated)
values
    ('nick@archilios.be', '$2a$12$df4AxNKuBK9BPkXQ6iDNReoDOMWv/f8ALz5urhn87ZfSN.o0HzbRW', 'ADMIN', true),
    ('kelly@archilios.be', '$2a$12$df4AxNKuBK9BPkXQ6iDNReoDOMWv/f8ALz5urhn87ZfSN.o0HzbRW', 'USER', true),
    ('fynn@archilios.be', '$2a$12$df4AxNKuBK9BPkXQ6iDNReoDOMWv/f8ALz5urhn87ZfSN.o0HzbRW', 'USER', false),
    ('mitch@archilios.be', '$2a$12$df4AxNKuBK9BPkXQ6iDNReoDOMWv/f8ALz5urhn87ZfSN.o0HzbRW', 'USER', false)
;