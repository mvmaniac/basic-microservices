drop table if exists tb_member cascade;

create table tb_member
(
    id           bigint auto_increment primary key,
    email        varchar(50)  not null,
    name         varchar(50)  not null,
    password     varchar(100) not null,
    unique_id    varchar(50)  not null,
    created_date datetime(6) default current_timestamp(6),
    updated_date datetime(6) default current_timestamp(6),
    constraint uk_member_unique_id unique (unique_id),
    constraint uk_member_email unique (email)
)
default character set = utf8mb4
collate = utf8mb4_unicode_ci
;
