create table answer (
            answer_id bigint not null,
            answer integer,
            answers_id integer,
            primary key (answer_id)) engine=InnoDB;

create table completed_quiz (
            id bigint not null,
            completed_at varchar(255),
            quiz_id integer,
            completed_id bigint,
            primary key (id)) engine=InnoDB;

create table hibernate_sequence (
    next_val bigint) engine=InnoDB;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table option_of_quiz (
            option_id bigint not null,
            option_of_quiz varchar(255),
            option_of_quizzes_id integer,
            primary key (option_id)) engine=InnoDB;

create table quiz (
          id integer not null,
          creator varchar(255),
          text varchar(255),
          title varchar(255),
          primary key (id)) engine=InnoDB;

create table user (
          id bigint not null,
          active bit,
          email varchar(255),
          password varchar(255),
          roles varchar(255),
          primary key (id)) engine=InnoDB;

alter table answer add constraint FKm94iax170cvjxj71k5q4ydasx foreign key (answers_id) references quiz (id);
alter table completed_quiz add constraint FK3mjgwo1jpb67wxqxgdwgg1t6w foreign key (completed_id) references user (id);
alter table option_of_quiz add constraint FKhv4khp37761dfdusfwt1xiedt foreign key (option_of_quizzes_id) references quiz (id);








# create table answer (answer_id bigint not null, answer integer, answers_id integer, primary key (answer_id)) engine=InnoDB;
# create table completed_quiz (id bigint not null, completed_at varchar(255), quiz_id integer, completed_id bigint, primary key (id)) engine=InnoDB;
# create table hibernate_sequence (next_val bigint) engine=InnoDB;
# insert into hibernate_sequence values ( 1 );
# insert into hibernate_sequence values ( 1 );
# insert into hibernate_sequence values ( 1 );
# insert into hibernate_sequence values ( 1 );
# insert into hibernate_sequence values ( 1 );
# create table option_of_quiz (option_id bigint not null, option_of_quiz varchar(255), option_of_quizs_id integer, primary key (option_id)) engine=InnoDB;
# create table quiz (id integer not null, creator varchar(255), text varchar(255), title varchar(255), primary key (id)) engine=InnoDB;
# create table user (id bigint not null, active bit, email varchar(255), password varchar(255), roles varchar(255), primary key (id)) engine=InnoDB;
# alter table answer add constraint FKm94iax170cvjxj71k5q4ydasx foreign key (answers_id) references quiz (id);
# alter table completed_quiz add constraint FK3mjgwo1jpb67wxqxgdwgg1t6w foreign key (completed_id) references user (id);
# alter table option_of_quiz add constraint FKhv4khp37761dfdusfwt1xiedt foreign key (option_of_quizs_id) references quiz (id);