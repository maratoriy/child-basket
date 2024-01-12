--liquibase formatted sql

--changeset moratorium:order_dishes_create
CREATE TABLE IF NOT EXISTS  order_dishes
(
    order_id INTEGER REFERENCES customer_order (order_id),
    dish_id  INTEGER REFERENCES dish (dish_id),
    PRIMARY KEY (order_id, dish_id)
);