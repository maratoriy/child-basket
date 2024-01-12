--liquibase formatted sql

--changeset moratorium:delivery_status_create
CREATE TABLE IF NOT EXISTS  delivery_status
(
    status_id SERIAL PRIMARY KEY,
    status    VARCHAR(255) NOT NULL
);