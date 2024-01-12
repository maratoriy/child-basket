--liquibase formatted sql

--changeset moratorium:review_create
CREATE TABLE IF NOT EXISTS review
(
    review_id    SERIAL PRIMARY KEY,
    parent_id    INTEGER                                               NOT NULL REFERENCES parent (parent_id),
    rating       SMALLINT CHECK (rating >= 1 AND rating <= 5),
    comments     TEXT,
    created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

--changeset moratorium:review_parent_id_index
CREATE INDEX IF NOT EXISTS  idx_review_parent_id ON review (parent_id);

--changeset moratorium:review_created_date_index
CREATE INDEX IF NOT EXISTS  idx_review_created_date ON review (created_date);