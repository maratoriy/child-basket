--liquibase formatted sql

--changeset moratorium:dish_create
CREATE TABLE IF NOT EXISTS  dish
(
    dish_id       SERIAL PRIMARY KEY,
    dish_type_id  INTEGER NOT NULL REFERENCES dish_type (type_id),
    name          VARCHAR(255) UNIQUE NOT NULL,
    description   TEXT,
    calories      SMALLINT CHECK (calories > 0),
    proteins      SMALLINT CHECK (proteins >= 0),
    fats          SMALLINT CHECK (fats >= 0),
    carbohydrates SMALLINT CHECK (carbohydrates >= 0)
);