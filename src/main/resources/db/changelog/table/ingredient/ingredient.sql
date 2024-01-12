--liquibase formatted sql

--changeset moratorium:ingredient_create
CREATE TABLE IF NOT EXISTS  ingredient
(
    ingredient_id SERIAL PRIMARY KEY,
    name          VARCHAR(255) UNIQUE NOT NULL
);