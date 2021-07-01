drop table if exists tb_order cascade;

create table tb_order
(
    id                bigint auto_increment primary key,
    unique_id         varchar(255) not null,
    member_unique_id  varchar(255) not null,
    product_unique_id varchar(120) not null,
    quantity          int          not null,
    total_price       int          not null,
    unit_price        int          not null,
    created_date      datetime(6) default current_timestamp(6),
    updated_date      datetime(6) default current_timestamp(6),
    constraint uk_order_unique_id unique (unique_id)
)
default character set = utf8mb4
collate = utf8mb4_unicode_ci
;
