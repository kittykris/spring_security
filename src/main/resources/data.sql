insert into roles values (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');
insert into users value (1, 25, 'user', 'user', 'user', 'user', 'user');
#password "user"
insert into users value (2, 30, 'admin', 'admin', 'admin', 'admin', 'admin');
#password "admin"
insert into users value (3, 30, 'user1', 'user1', 'user1', 'user1', 'user1');
#password "user1"
insert into users_roles values (1, 1), (2, 2), (3, 1), (3, 2), (2, 1);