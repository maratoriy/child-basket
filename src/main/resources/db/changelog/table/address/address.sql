--liquibase formatted sql

--changeset moratorium:address_create
CREATE TABLE IF NOT EXISTS  address
(
    address_id     SERIAL PRIMARY KEY,
    parent_id      INTEGER NOT NULL REFERENCES parent (parent_id),
    region         VARCHAR(255),
    city           VARCHAR(255),
    street         VARCHAR(255),
    house          VARCHAR(10),
    apartment      VARCHAR(10),
    postal_code    VARCHAR(6) CHECK (postal_code ~ '^[0-9]{6}$'),
    delivery_notes TEXT,
    is_default     BOOLEAN NOT NULL,
    CHECK (is_default IN (TRUE, FALSE))
);

--changeset moratorium:address_parent_id_index
CREATE INDEX IF NOT EXISTS  idx_address_parent_id ON address (parent_id);

--changeset moratorium:address_parent_city_index
CREATE INDEX IF NOT EXISTS  idx_address_city ON address (city);