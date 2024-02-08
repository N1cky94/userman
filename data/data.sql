insert into users
(email,password,role, account_activated, first_name, last_name)
values
    ('nick@archilios.be', '$2a$12$df4AxNKuBK9BPkXQ6iDNReoDOMWv/f8ALz5urhn87ZfSN.o0HzbRW', 'ADMIN', true, 'Nick', 'Bauters'),
    ('kelly@archilios.be', '$2a$12$df4AxNKuBK9BPkXQ6iDNReoDOMWv/f8ALz5urhn87ZfSN.o0HzbRW', 'USER', true, 'Kelly', 'Bauters'),
    ('fynn@archilios.be', '$2a$12$df4AxNKuBK9BPkXQ6iDNReoDOMWv/f8ALz5urhn87ZfSN.o0HzbRW', 'USER', false, 'Fynn', 'Bauters'),
    ('mitch@archilios.be', '$2a$12$df4AxNKuBK9BPkXQ6iDNReoDOMWv/f8ALz5urhn87ZfSN.o0HzbRW', 'USER', false, 'Mitch', 'Bauters')
;