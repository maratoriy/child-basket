--liquibase formatted sql

--changeset moratorium:dish_ingredients_create
CREATE TABLE IF NOT EXISTS  dish_ingredients
(
    dish_id       INTEGER NOT NULL REFERENCES dish (dish_id),
    ingredient_id INTEGER NOT NULL REFERENCES ingredient (ingredient_id),
    PRIMARY KEY (dish_id, ingredient_id)
);