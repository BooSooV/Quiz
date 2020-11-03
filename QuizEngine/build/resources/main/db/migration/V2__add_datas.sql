insert into user(id, active, email, password, roles) values
(1, true, 'test@gmail.com', 'secret', 'ROLE_ADMIN');

# Add start Quizzes
insert into quiz(id, creator, text, title) values
(1, 'test@gmail.com', 'What characters can the name of a variable contain?', 'Name of a variable');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(1, 'Numbers', 1),
(2, 'Letters', 1),
(3, '$', 1),
(4, '#', 1);

insert into answer(answer_id, answer, answers_id) values
(1, 0, 1),
(2, 1, 1),
(3, 2, 1);
# ------------------------------------------------------
insert into quiz(id, creator, text, title) values
(2, 'test@gmail.com', 'What is not a primitive datatype?', 'Primitive datatype');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(5, 'Integer', 2),
(6, 'char', 2),
(7, 'String', 2),
(8, 'double', 2);

insert into answer(answer_id, answer, answers_id) values
(4, 0, 2),
(5, 2, 2);
# ------------------------------------------------------
insert into quiz(id, creator, text, title) values
(3, 'test@gmail.com', 'Which data type conversion is guaranteed not to result in data loss?', 'Data type conversion');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(9, 'bute -> int', 3),
(10, 'short -> long', 3),
(11, 'long -> bute', 3),
(12, 'long -> double', 3);

insert into answer(answer_id, answer, answers_id) values
(6, 0, 3),
(7, 1, 3);
# ------------------------------------------------------
insert into quiz(id, creator, text, title) values
(4, 'test@gmail.com', 'What is not a logical operator?', 'Logical operators');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(13, '&', 4),
(14, '&&', 4),
(15, '||', 4),
(16, '??', 4);

insert into answer(answer_id, answer, answers_id) values
(8, 3, 4);
# ------------------------------------------------------
insert into quiz(id, creator, text, title) values
(5, 'test@gmail.com', 'What is not a cycle?', 'Cycles');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(17, 'for', 5),
(18, 'do-while', 5),
(19, 'while-do', 5),
(20, 'while', 5);

insert into answer(answer_id, answer, answers_id) values
(9, 2, 5);
# ------------------------------------------------------
insert into quiz(id, creator, text, title) values
(6, 'test@gmail.com', 'Choose OOP principles.', 'OOP principles.');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(21, 'Encapsulation', 6),
(22, 'Polymorphism', 6),
(23, 'Serialization', 6),
(24, 'Centralization', 6);

insert into answer(answer_id, answer, answers_id) values
(10, 0, 6),
(11, 1, 6);
# ------------------------------------------------------
insert into quiz(id, creator, text, title) values
(7, 'test@gmail.com', 'What is encapsulation?', 'Encapsulation?');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(25, 'Hiding fields and methods from incorrect use.', 7),
(26, 'Converting one data type to another.', 7),
(27, 'Super class extension.', 7),
(28, 'Converting program to bytecode.', 7);

insert into answer(answer_id, answer, answers_id) values
(12, 0, 7);
# ------------------------------------------------------
insert into quiz(id, creator, text, title) values
(8, 'test@gmail.com', 'What is inheritance?', 'Inheritance?');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(29, 'Getting fields and methods from the parent class.', 8),
(30, 'Inheritance from relatives.', 8),
(31, 'Obtaining a genetic code from parents.', 8),
(32, 'Converting one type of variables to another.', 8);

insert into answer(answer_id, answer, answers_id) values
(13, 0, 8);
# ------------------------------------------------------
insert into quiz(id, creator, text, title) values
(9, 'test@gmail.com', 'What is the access level modifier?', 'Access level modifier?');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(33, 'private', 9),
(34, 'public', 9),
(35, 'protected', 9),
(36, 'final', 9);

insert into answer(answer_id, answer, answers_id) values
(14, 0, 9),
(15, 1, 9),
(16, 2, 9);
# ------------------------------------------------------
insert into quiz(id, creator, text, title) values
(10, 'test@gmail.com', 'What is not a method of the Object class?', 'Methods Object class?');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(37, 'clone', 10),
(38, 'equals', 10),
(39, 'get', 10),
(40, 'set', 10);

insert into answer(answer_id, answer, answers_id) values
(17, 2, 10),
(18, 3, 10);
# ------------------------------------------------------
insert into quiz(id, creator, text, title) values
(11, 'test@gmail.com', 'What exceptions are checked?', 'Checked exceptions');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(41, 'SQLExeption', 11),
(42, 'IndexOutOfBoundsExeption', 11),
(43, 'IOExeption', 11),
(44, 'ClassCastExeption', 11);

insert into answer(answer_id, answer, answers_id) values
(19, 0, 11),
(20, 2, 11);
# ------------------------------------------------------
insert into quiz(id, creator, text, title) values
(12, 'test@gmail.com', 'What doesnt implement the Collection interface?', 'Collection interface');

insert into option_of_quiz(option_id, option_of_quiz, option_of_quizzes_id) values
(45, 'ArrayList', 12),
(46, 'HashMap', 12),
(47, 'LinkedList', 12),
(48, 'Treeset', 12);

insert into answer(answer_id, answer, answers_id) values
(21, 1, 12);
# ------------------------------------------------------