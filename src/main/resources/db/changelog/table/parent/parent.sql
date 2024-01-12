--liquibase formatted sql

--changeset moratorium:parent_create
CREATE TABLE IF NOT EXISTS parent
(
    parent_id         SERIAL PRIMARY KEY,
    surname           VARCHAR(255)                                          NOT NULL,
    first_name        VARCHAR(255)                                          NOT NULL,
    middle_name       VARCHAR(255)                                          NOT NULL,
    phone_number      VARCHAR(20) UNIQUE CHECK (phone_number ~ '^[0-9]+$'),
    registration_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    login_date        TIMESTAMP WITHOUT TIME ZONE,
    password              TEXT                                              NOT NULL
);

--changeset moratorium:parent_phone_number_index
CREATE INDEX IF NOT EXISTS idx_phone_number ON parent USING HASH (phone_number);