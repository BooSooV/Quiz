delete from answer WHERE `answer_id` > 0;
delete from option_of_quiz WHERE `option_id` > 0;
delete from quiz WHERE `id` > 0;

insert into quiz(id, creator, text, title) values
(1, 'test@gmail.com', 'Select only tea drinks.', 'Tea drinks');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(1, 'black tea', 1),
(2, 'green tea', 1),
(3, 'Cappuccino', 1),
(4, 'Sprite', 1);

insert into answer(answer_id, answer, answers_id) values
(1, 0, 1),
(2, 1, 1);

