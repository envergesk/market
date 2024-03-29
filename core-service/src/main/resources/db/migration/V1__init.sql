create table categories
(
    id              bigserial primary key,
    title           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table products
(
    id bigserial    primary key,
    title           varchar(255),
    price           numeric(8, 2),
    category_id     bigint references categories(id),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into categories (title) values ('Еда');

insert into products (title, price, category_id)
values ('Молоко', 100.20, 1),
       ('Молоко2', 66.20, 1),
       ('Молоко3', 76.20, 1),
       ('Молоко4', 122.20, 1),
       ('Молоко5', 321.20, 1),
       ('Молоко6', 88.20, 1),
       ('Молоко7', 100.20, 1),
       ('Хлеб', 80.20, 1),
       ('Сыр', 90.20, 1),
       ('Масло', 320.00, 1);

create table orders
(
    id          bigserial primary key,
    username    varchar(255),
    total_price numeric(8, 2) not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                  bigserial primary key,
    product_id          bigint not null references products (id),
    order_id            bigint not null references orders (id),
    quantity            int    not null,
    price_per_product   numeric(8, 2)    not null,
    price               numeric(8, 2)    not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);