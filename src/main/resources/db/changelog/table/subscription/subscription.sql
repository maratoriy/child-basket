--liquibase formatted sql

--changeset moratorium:subscription_create
CREATE TABLE IF NOT EXISTS  subscription
(
    subscription_id SERIAL PRIMARY KEY,
    plan_id         INTEGER  NOT NULL REFERENCES subscription_plan (plan_id),
    child_id        INTEGER  NOT NULL REFERENCES child (child_id),
    meal_left       SMALLINT NOT NULL CHECK (meal_left >= 0),
    start_date      DATE     NOT NULL,
    end_date        DATE
);

--changeset moratorium:subscription_plan_id_index
CREATE INDEX IF NOT EXISTS  idx_subscription_plan_id ON subscription (plan_id);

--changeset moratorium:subscription_child_id_index
CREATE INDEX IF NOT EXISTS  idx_subscription_child_id ON subscription (child_id);
