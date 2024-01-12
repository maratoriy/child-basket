--liquibase formatted sql

--changeset moratorium:customer_order_create
CREATE TABLE IF NOT EXISTS  customer_order
(
    order_id           SERIAL PRIMARY KEY,
    delivery_status_id INTEGER                                               NOT NULL REFERENCES delivery_status (status_id),
    delivery_date      TIMESTAMP WITHOUT TIME ZONE,
    created_date       TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date       TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    address_id         INTEGER REFERENCES address (address_id),
    subscription_id    INTEGER REFERENCES subscription (subscription_id)
);

--changeset moratorium:customer_order_delivery_status_id_index
CREATE INDEX IF NOT EXISTS  idx_order_delivery_status_id ON customer_order (delivery_status_id);

--changeset moratorium:customer_order_created_date_index
CREATE INDEX IF NOT EXISTS  idx_order_created_date_id ON customer_order (created_date);

--changeset moratorium:customer_order_subscription_id_index
CREATE INDEX IF NOT EXISTS  idx_order_subscription_id ON customer_order (subscription_id);

--changeset moratorium:customer_order_address_id_index
CREATE INDEX IF NOT EXISTS  idx_order_address_id ON customer_order (address_id);