--liquibase formatted sql

--changeset moratorium:subscription_plan_create
CREATE TABLE IF NOT EXISTS  subscription_plan
(
    plan_id        SERIAL PRIMARY KEY,
    dish_type_id   INTEGER      NOT NULL REFERENCES dish_type (type_id),
    name           VARCHAR(255) NOT NULL,
    description    TEXT,
    price          SMALLINT CHECK (price > 0),
    meal_frequency SMALLINT CHECK (meal_frequency > 0),
    meal_per_order SMALLINT CHECK (meal_per_order > 0)
);