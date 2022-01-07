insert into roles values (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');
insert into users values (1, 25, 'user', 'user', 'user', 'user', 'user')
                       , (2, 30, 'admin', 'admin', 'admin', 'admin', 'admin');
insert into users_roles values (1, 1), (2, 2);
select * from users;