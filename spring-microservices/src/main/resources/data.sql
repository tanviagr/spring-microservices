insert into user values(1001, sysdate(), 'Rahul');
insert into user values(1002, sysdate(), 'Ankit');
insert into user values(1003, sysdate(), 'Swapnil');
--the sequence hibernate generates is from 1 for ID - quick fix
--can only use single quotes in SQL files - otherwise value not found

--the link to h2 console is http://localhost:8080/h2-console