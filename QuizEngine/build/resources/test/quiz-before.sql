delete from answer;
delete from option;
delete from quiz;

delete from completed_quiz;

insert into quiz(id, creator, text, title) values
(1, 'test@gmail.com', 'Select only tea drinks.', 'Tea drinks');

insert into option(option_id, option, options_id) values
(1, 'black tea', 1),
(2, 'green tea', 1),
(3, 'Cappuccino', 1),
(4, 'Sprite', 1);

insert into answer(answer_id, answer, answers_id) values
(1, 0, 1),
(2, 1, 1);
