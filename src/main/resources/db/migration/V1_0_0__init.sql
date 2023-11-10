create table if not exists public.user_table
(
    id       serial
        constraint user_pk
            primary key,
    name     varchar(255) not null
        constraint user_name
            unique,
    password varchar(255) not null,
    blocked  boolean      not null
);

create table if not exists public.message
(
    id       serial
    constraint message_pk
    primary key,
    user_id  integer      not null
    constraint message_user_id_fk
    references public.user_table,
    datetime timestamp    not null,
    text     varchar(255) not null
    );



