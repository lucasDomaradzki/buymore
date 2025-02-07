create table if not exists product (
    id bigint not null auto_increment,
    name varchar(60) not null,
    starting_price numeric(9,2) not null,
    discount numeric(9,2),
    profit_margin float(2) not null,
    description varchar(750) not null,
    category varchar(60),
    active tinyint(1) not null,
    comments varchar(120),
    created_at datetime not null,
    updated_at datetime not null,
    constraint pk_product primary key(id),
    constraint uk_product_name unique (name)
);
