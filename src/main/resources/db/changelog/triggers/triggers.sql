--liquibase formatted sql

--changeset moratorium:trigger_validate_subscription_dates
CREATE OR REPLACE FUNCTION validate_subscription_dates()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.start_date > NEW.end_date THEN
        RAISE EXCEPTION 'Start date must not be after end date.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER subscription_dates_trigger
    BEFORE INSERT OR UPDATE ON subscription
    FOR EACH ROW
EXECUTE FUNCTION validate_subscription_dates();

--changeset moratorium:validate_order_delivery_date
CREATE OR REPLACE FUNCTION validate_order_delivery_date()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.delivery_date < (SELECT start_date FROM subscription WHERE subscription_id = NEW.subscription_id) THEN
        RAISE EXCEPTION 'Delivery date must be the subscription start date.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER order_delivery_date_trigger
    BEFORE INSERT OR UPDATE ON customer_order
    FOR EACH ROW
EXECUTE FUNCTION validate_order_delivery_date();

--changeset moratorium:trigger_validate_order_future_delivery_date
CREATE OR REPLACE FUNCTION validate_order_future_delivery_date()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.delivery_date < CURRENT_DATE THEN
        RAISE EXCEPTION 'Delivery date must be in the future.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER order_future_delivery_date_trigger
    BEFORE INSERT OR UPDATE ON customer_order
    FOR EACH ROW
EXECUTE FUNCTION validate_order_future_delivery_date();

--changeset moratorium:trigger_set_updated_date
CREATE OR REPLACE FUNCTION set_updated_date()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_date := CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER child_updated_date_trigger
    BEFORE UPDATE ON child
    FOR EACH ROW
EXECUTE FUNCTION set_updated_date();
CREATE TRIGGER order_updated_date_trigger
    BEFORE UPDATE ON customer_order
    FOR EACH ROW
EXECUTE FUNCTION set_updated_date();