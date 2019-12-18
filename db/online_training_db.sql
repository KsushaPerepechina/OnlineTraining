create table users
(
    id              int auto_increment,
    first_name      varchar(35)                                                         not null,
    last_name       varchar(30)                                                         not null,
    birth_date      date                                                                not null,
    email           varchar(40)                                                         not null,
    phone_number    varchar(13)                                                         not null,
    password        varchar(200)                                                        not null,
    blocking_status enum ('active', 'blocked')                        default 'active'  not null,
    role            enum ('student', 'main admin', 'admin', 'mentor') default 'student' not null,
    balance         decimal                                           default 0         not null,
    activity        enum ('on', 'off')                                default 'on'      not null,
    constraint user_id_uindex
        unique (id)
);

alter table users
    add primary key (id);

create table trainings
(
    id         int auto_increment,
    name       varchar(30)                                                                          not null,
    start_date date                                                                                 null,
    end_date   date                                                                                 null,
    progress   enum ('registration opened', 'in process', 'finished') default 'registration opened' not null,
    mentor_id  int                                                                                  not null,
    activity   enum ('on', 'off')                                     default 'on'                  not null,
    constraint table_name_id_uindex
        unique (id),
    constraint trainings_users_id_fk
        foreign key (mentor_id) references users (id)
);

alter table trainings
    add primary key (id);

create table assignments
(
    id          int auto_increment,
    name        varchar(30)                     not null,
    type        enum ('task', 'topic')          not null,
    training_id int                             not null,
    activity    enum ('on', 'off') default 'on' not null,
    constraint assignment_id_uindex
        unique (id),
    constraint assignments_trainings_id_fk
        foreign key (training_id) references trainings (id)
);

alter table assignments
    add primary key (id);

create table consultations
(
    id          int auto_increment,
    student_id  int                                                              not null,
    training_id int                                                              not null,
    date        date                                                             null,
    cost        decimal                                      default 5           null,
    status      enum ('requested', 'scheduled', 'completed') default 'requested' not null,
    performance tinyint(1)                                   default 0           not null,
    quality     tinyint(1)                                   default 0           not null,
    payed       tinyint(1)                                   default 0           not null,
    activity    enum ('on', 'off')                           default 'on'        not null,
    constraint consultation_id_uindex
        unique (id),
    constraint consultations_trainings_id_fk
        foreign key (training_id) references trainings (id),
    constraint consultations_users_id_fk
        foreign key (student_id) references users (id)
);

alter table consultations
    add primary key (id);

create table consultation_assignments
(
    id              int auto_increment,
    consultation_id int                             null,
    assignment_id   int                             null,
    activity        enum ('on', 'off') default 'on' not null,
    constraint consultation_task_id_uindex
        unique (id),
    constraint consultation_tasks_consultations_id_fk
        foreign key (consultation_id) references consultations (id),
    constraint consultation_tasks_tasks_id_fk
        foreign key (assignment_id) references assignments (id)
);

alter table consultation_assignments
    add primary key (id);

create table records
(
    id          int auto_increment,
    student_id  int                                                                                                   not null,
    training_id int                                                                                                   not null,
    mark        tinyint                                                                           default 0           null,
    status      enum ('requested', 'approved', 'rejected', 'in process', 'expelled', 'completed') default 'requested' not null,
    activity    enum ('on', 'off')                                                                default 'on'        not null,
    constraint student_training_record_id_uindex
        unique (id),
    constraint student_training_history_trainings_id_fk
        foreign key (training_id) references trainings (id),
    constraint student_training_history_users_id_fk
        foreign key (student_id) references users (id)
);

alter table records
    add primary key (id);

create table transactions
(
    id             int auto_increment,
    payer_id       int                        not null,
    date           date                       not null,
    operation_type enum ('refill', 'payment') not null,
    sum            decimal(5, 2)              not null,
    constraint transactions_id_uindex
        unique (id),
    constraint transactions_users_id_fk
        foreign key (payer_id) references users (id)
);

alter table transactions
    add primary key (id);


