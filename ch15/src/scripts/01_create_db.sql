create schema order_system;

use order_system;

create table customer
(
    customer_id integer not null auto_increment,
    name        varchar(20) not null,
    address     varchar(60) null,
    email       varchar(40) null,
    primary key (customer_id)
);

create table order_item
(
    order_item_id   integer not null auto_increment,
    amount          integer not null,
    product_id      integer null,
    order_id        integer null,
    primary key (order_item_id)
);

create table orders
(
    order_id        integer not null auto_increment,
    order_date      date not null,
    customer_id     integer null,
    primary key (order_id)
);

create table product
(
    product_id      integer not null auto_increment,
    name            varchar(20) not null,
    price           integer not null,
    description     varchar(60) null,
    primary key (product_id)
);

alter table order_item add foreign key R_5 (product_id) references product (product_id);
alter table order_item add foreign key R_7 (order_id) references orders (order_id);
alter table orders add foreign key R_6 (customer_id) references customer (customer_id);