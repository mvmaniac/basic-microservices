drop table if exists tb_catalog cascade;

create table tb_catalog
(
    id                bigint auto_increment primary key,
    product_unique_id varchar(120) not null,
    product_name      varchar(255) not null,
    stock             int          not null,
    unit_price        int          not null,
    created_date      datetime(6) default current_timestamp(6),
    updated_date      datetime(6) default current_timestamp(6),
    constraint uk_catalog_product_unique_id unique (product_unique_id)
)
default character set = utf8mb4
collate = utf8mb4_unicode_ci
;
