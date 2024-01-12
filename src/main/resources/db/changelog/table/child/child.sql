--liquibase formatted sql

--changeset moratorium:child_create
CREATE TABLE IF NOT EXISTS  child
(
    child_id      SERIAL PRIMARY KEY,
    parent_id     INTEGER                                               NOT NULL REFERENCES parent (parent_id),
    name          VARCHAR(255)                                          NOT NULL,
    birthday_date TIMESTAMP WITHOUT TIME ZONE,
    created_date  TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date  TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

--changeset moratorium:child_parent_id_index
CREATE INDEX IF NOT EXISTS  idx_child_parent_id ON child (parent_id);