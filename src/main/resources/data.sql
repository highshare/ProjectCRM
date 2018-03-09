insert into roles values(1, "ROLE_USER");
insert into roles values(2, "ROLE_ADMIN");

insert into users values(1, 1, '2018-02-15 10:05:58', 'jakub.malinowski@company.com', 'Jakub', 'Malinowski', '$2a$10$ei9ovqWL31J36O5Gu/UtaODwUZ8YCxIUXD7C3aLlb14ukoQxWk4Qa', '555-001-001', 0, 'admin');
insert into users values(2, 1, '2018-03-08 15:29:48', 'paulina.sobczak@company.com', 'Paulina', 'Sobczak', '$2a$10$MTe7iJnmpwFe0tSiIMYB/uesZmHnx2dq4GzD3NTcEfpRj.JvWeTxS', '700-300-100', 0, 'pausob');
insert into users values(3, 1, '2018-03-08 15:35:42', 'marian.chmielewski@company.com', 'Marian', 'Chmielewski', '$2a$10$p9ONd5YqP/RSmT.vK9H57uLD/7CG189Qt0mfT6/70nqQU9LWB7QPu', '605-303-030', 0, 'marchmiel');
insert into users values(4, 1, '2018-03-08 15:53:15', 'joanna.baranowska@company.com', 'Joanna', 'Baranowska', '$2a$10$8Gzzp5yEhICjZU7u065wQudKYur9LQUGIgm4qTscaaDxMBcdGZeD2', '602-400-500', 1, 'joannabar');
insert into users values(5, 1, '2018-03-08 15:55:34', 'wanda.krawczyk@company.com', 'Wanda', 'Krawczyk', '$2a$10$QuIzx6Lnv8zDMUTh3M8gSuE1kia3R7dw4sufrjKiSkQENQpNoEmDO', '888-100-100', 2, 'wankra');
insert into users values(6, 1, '2018-03-09 15:03:39', 'roman.sawicki@company.com', 'Roman', 'Sawicki', '$2a$10$i9kndLe6x4mMf749ViG6NOWUCylA73QucpZxoqKtuGgnOrij4tnBC', '654-312-200', 0, 'romsaw');
insert into users values(7, 1, '2018-03-09 15:12:57', 'renata.urbanska@company.com', 'Renata', 'Urbanska', '$2a$10$Ni4eiAhKcI/vfz1jN2y.yOzz5h5SyCA.l3NxK2N.68F4e/yYflJn.', '616-300-252', 0, 'renurb');
insert into users values(8, 1, '2018-03-09 15:19:07', 'ewelina.malinowska@company.com', 'Ewelina', 'Malinowska', '$2a$10$T9LEBT497RYw6aH4zBPpEOTrKvWAvXdzaZTCgQ7LqesiDioHLYmCW', '700-350-230', 0, 'ewemalin');
insert into users values(9, 1, '2018-03-09 15:21:40', 'anna.krajewska@company.com', 'Anna', 'Krajewska', '$2a$10$KbJ.8NtnZaGKwRuf6IaTcOSweI225ef0QaE7PkMS0uIYh3IuyNEhe', '601-859-320', 0, 'annkraj');
insert into users values(10, 1, '2018-03-09 15:23:27', 'agata.kubiak@company.com', 'Agata', 'Kubiak', '$2a$10$M9Dxp6YAt9kfNREIlckJieMJuXNDKwO/BG0PBzsVyGUal7/WBDYNm', '510-610-355', 1, 'agakub');


insert into users_roles values(2, 1);
insert into users_roles values(3, 1);
insert into users_roles values(4, 1);
insert into users_roles values(5, 1);
insert into users_roles values(1, 2);
insert into users_roles values(5, 2);
insert into users_roles values(6, 1);
insert into users_roles values(7, 1);
insert into users_roles values(8, 1);
insert into users_roles values(9, 1);
insert into users_roles values(10, 1);


insert into teams values(1, 'Sales Team', 4);
insert into teams values(2, 'Support Team', 10);

insert into teams_members values(1, 2);
insert into teams_members values(1, 3);
insert into teams_members values(1, 6);
insert into teams_members values(2, 7);
insert into teams_members values(2, 8);
insert into teams_members values(2, 9);


