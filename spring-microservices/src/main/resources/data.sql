insert into user values(1001, sysdate(), 'Shruti');
insert into user values(1002, sysdate(), 'Anushka');
insert into user values(1003, sysdate(), 'Rithija');
--the sequence hibernate generates is from 1 for ID - quick fix
--can only use single quotes in SQL files - otherwise value not found
insert into post values(11000, 'this is the first post by Shruti', 1001);
insert into post values(11001, 'this is the second post by Shruti', 1001);
--the link to h2 console is http://localhost:8080/h2-console