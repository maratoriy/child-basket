--liquibase formatted sql

--changeset moratorium:dish_allergens_create
CREATE TABLE IF NOT EXISTS  dish_allergens
(
    dish_id     INTEGER NOT NULL REFERENCES dish (dish_id),
    allergen_id INTEGER NOT NULL REFERENCES allergen (allergen_id),
    PRIMARY KEY (dish_id, allergen_id)
);