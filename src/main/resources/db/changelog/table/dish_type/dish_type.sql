--liquibase formatted sql

--changeset moratorium:dish_type_create
CREATE TABLE IF NOT EXISTS  dish_type
(
    type_id     SERIAL PRIMARY KEY,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description TEXT
);