create table if not exists contact
(
    id bigint not null
        constraint contact_pkey
            primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    phone varchar(255) not null
);

create table if not exists message
(
    id bigint not null
        constraint message_pkey
            primary key,
    contact_message varchar(255) not null,
    contact_id bigint not null,
    message_direction varchar(255)
);

create sequence contact_id_seq;

create sequence message_id_seq;