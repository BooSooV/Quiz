delete from completed_quiz;

delete from user;





insert into user(id, active, email, password, roles) values
(1, 'true', 'test@gmail.com', 'secret', 'ROLE_ADMIN');

insert into completed_quiz(id, completed_at, quiz_id, completed_id) values
(1, '2020-8-21T13:3:57.46320200', 1, 1);