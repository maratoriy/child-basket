--liquibase formatted sql

--changeset moratorium:allergen_create
CREATE TABLE IF NOT EXISTS  allergen
(
    allergen_id SERIAL PRIMARY KEY,
    name        VARCHAR(255) UNIQUE NOT NULL
);