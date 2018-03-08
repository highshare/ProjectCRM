insert into roles values(1, "ROLE_USER");
insert into roles values(2, "ROLE_ADMIN");

insert into users values(1, 1, '2018-02-15 10:05:58', 'jakub.malinowski@company.com', 'Jakub', 'Malinowski', '$2a$10$ei9ovqWL31J36O5Gu/UtaODwUZ8YCxIUXD7C3aLlb14ukoQxWk4Qa', '555-001-001', 0, 'admin');
insert into users values(2, 1, '2018-03-08 15:29:48', 'paulina.sobczak@company.com', 'Paulina', 'Sobczak', '$2a$10$MTe7iJnmpwFe0tSiIMYB/uesZmHnx2dq4GzD3NTcEfpRj.JvWeTxS', '700-300-100', 0, 'pausob');
insert into users values(3, 1, '2018-03-08 15:35:42', 'marian.chmielewski@company.com', 'Marian', 'Chmielewski', '$2a$10$p9ONd5YqP/RSmT.vK9H57uLD/7CG189Qt0mfT6/70nqQU9LWB7QPu', '605-303-030', 0, 'marchmiel');
insert into users values(4, 1, '2018-03-08 15:53:15', 'joanna.baranowska@company.com', 'Joanna', 'Baranowska', '$2a$10$8Gzzp5yEhICjZU7u065wQudKYur9LQUGIgm4qTscaaDxMBcdGZeD2', '602-400-500', 1, 'joannabar');
insert into users values(5, 1, '2018-03-08 15:55:34', 'wanda.krawczyk@company.com', 'Wanda', 'Krawczyk', '$2a$10$QuIzx6Lnv8zDMUTh3M8gSuE1kia3R7dw4sufrjKiSkQENQpNoEmDO', '888-100-100', 2, 'wankra');

insert into users_roles values(2, 1);
insert into users_roles values(3, 1);
insert into users_roles values(4, 1);
insert into users_roles values(5, 1);
insert into users_roles values(1, 2);
insert into users_roles values(5, 2);



