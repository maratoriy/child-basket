-- Регистрация и управление подписками
CREATE OR REPLACE PROCEDURE register_parent(
    IN p_surname VARCHAR(255),
    IN p_first_name VARCHAR(255),
    IN p_middle_name VARCHAR(255),
    IN p_phone_number VARCHAR(20),
    IN password TEXT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO parent (surname, first_name, middle_name, phone_number, password)
    VALUES (p_surname, p_first_name, p_middle_name, p_phone_number, password);
END;
$$;

-- Добавить ребенка
CREATE OR REPLACE PROCEDURE add_child(
    IN p_parent_id INTEGER,
    IN p_name VARCHAR(255),
    IN p_birthday_date TIMESTAMP WITHOUT TIME ZONE
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO child (parent_id, name, birthday_date)
    VALUES (p_parent_id, p_name, p_birthday_date);
END;
$$;

-- Выбор подписочного плана
CREATE OR REPLACE PROCEDURE choose_subscription_plan(
    IN p_child_id INTEGER,
    IN p_plan_id INTEGER,
    IN p_end_date DATE
)
    LANGUAGE plpgsql
AS $$
DECLARE
    v_meal_frequency SMALLINT;
BEGIN
    SELECT meal_frequency INTO v_meal_frequency
    FROM subscription_plan
    WHERE plan_id = p_plan_id;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'Subscription plan with ID % not found.', p_plan_id;
    END IF;

    INSERT INTO subscription (plan_id, child_id, start_date, end_date, meal_left)
    VALUES (p_plan_id, p_child_id, CURRENT_DATE, p_end_date, v_meal_frequency);

END;
$$;

-- Продлить подписку
CREATE OR REPLACE PROCEDURE extend_subscription(
    IN p_subscription_id INTEGER,
    IN p_extension_months INTEGER
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE subscription
    SET end_date = COALESCE(end_date, start_date) + (p_extension_months || ' months')::interval
    WHERE subscription_id = p_subscription_id;
END;
$$;

-- Получить фильтрованный список заказов
CREATE OR REPLACE FUNCTION get_filtered_orders(
    p_start_date DATE,
    p_end_date DATE,
    p_status VARCHAR,
    p_city VARCHAR
)
    RETURNS TABLE(order_id INTEGER, order_status VARCHAR, order_city VARCHAR, order_date TIMESTAMP) AS $$
BEGIN
    RETURN QUERY
        SELECT co.order_id, ds.status, addr.city, co.created_date
        FROM customer_order co
                 JOIN delivery_status ds ON co.delivery_status_id = ds.status_id
                 JOIN address addr ON co.address_id = addr.address_id
        WHERE (co.created_date >= p_start_date AND co.created_date <= p_end_date)
          AND (p_status IS NULL OR ds.status = p_status)
          AND (p_city IS NULL OR addr.city = p_city)
        ORDER BY co.created_date DESC;
END;
$$ LANGUAGE plpgsql;

-- Разместить заказ
CREATE OR REPLACE PROCEDURE place_order(
    IN p_parent_id INTEGER,
    IN p_subscription_id INTEGER,
    IN p_delivery_date TIMESTAMP WITHOUT TIME ZONE,
    IN p_address_id INTEGER,
    IN p_selected_dishes INTEGER[]
)
    LANGUAGE plpgsql
AS $$
DECLARE
    v_subscription_plan_id INTEGER;
    v_order_id INTEGER;
    v_dish_id INTEGER;
    v_meal_per_order INTEGER;
BEGIN
    SELECT plan_id, meal_per_order INTO v_subscription_plan_id, v_meal_per_order
    FROM subscription_plan
    WHERE plan_id = (SELECT plan_id FROM subscription WHERE subscription_id = p_subscription_id);


    IF NOT EXISTS (
            SELECT 1
            FROM subscription s
                     JOIN child c ON s.child_id = c.child_id
                     JOIN parent p ON c.parent_id = p.parent_id
            WHERE s.subscription_id = p_subscription_id AND p.parent_id = p_parent_id
        ) THEN
        RAISE EXCEPTION 'Invalid subscription for the specified parent.';
    END IF;

    IF NOT array_length(p_selected_dishes, 1) = v_meal_per_order THEN
        RAISE EXCEPTION 'The total number of meals in the order is not equals to limit per day.';
    END IF;

    IF (SELECT meal_left FROM subscription WHERE subscription_id = p_subscription_id) <= 0 THEN
        RAISE EXCEPTION 'No meals left in the subscription.';
    END IF;

    INSERT INTO customer_order (delivery_status_id, delivery_date, address_id, subscription_id)
    VALUES (1, p_delivery_date, p_address_id, p_subscription_id)
    RETURNING order_id INTO v_order_id;

    FOREACH v_dish_id IN ARRAY p_selected_dishes
        LOOP
            IF EXISTS (
                    SELECT 1
                    FROM order_dishes
                    WHERE order_id = v_order_id AND dish_id = v_dish_id
                ) THEN
                UPDATE order_dishes
                SET quantity = quantity + 1
                WHERE order_id = v_order_id AND dish_id = v_dish_id;
            ELSE
                INSERT INTO order_dishes (order_id, dish_id, quantity)
                VALUES (v_order_id, v_dish_id, 1);
            END IF;
        END LOOP;

    UPDATE subscription
    SET meal_left = meal_left - 1
    WHERE subscription_id = p_subscription_id;

END;
$$;

-- CALL place_order(1, 1, '2023-02-01'::TIMESTAMP, 1, ARRAY[1, 2, 3]);

-- Добавить блюдо
CREATE OR REPLACE PROCEDURE add_dish(
    IN p_dish_type_id INTEGER,
    IN p_name VARCHAR(255),
    IN p_description TEXT,
    IN p_calories SMALLINT,
    IN p_proteins SMALLINT,
    IN p_fats SMALLINT,
    IN p_carbohydrates SMALLINT,
    IN p_ingredients INTEGER[],
    IN p_allergens INTEGER[]
)
    LANGUAGE plpgsql
AS $$
DECLARE
    v_dish_id INTEGER;
    v_ingredient_id INTEGER;
    v_allergen_id INTEGER;
BEGIN
    INSERT INTO dish (dish_type_id, name, description, calories, proteins, fats, carbohydrates)
    VALUES (p_dish_type_id, p_name, p_description, p_calories, p_proteins, p_fats, p_carbohydrates)
    RETURNING dish_id INTO v_dish_id;

    FOREACH v_ingredient_id IN ARRAY p_ingredients
        LOOP
            INSERT INTO dish_ingredients (dish_id, ingredient_id)
            VALUES (v_dish_id, v_ingredient_id);
        END LOOP;

    FOREACH v_allergen_id IN ARRAY p_allergens
        LOOP
            INSERT INTO dish_allergens (dish_id, allergen_id)
            VALUES (v_dish_id, v_allergen_id);
        END LOOP;

END;
$$;

-- Получить отзывы
CREATE OR REPLACE FUNCTION get_feedback(
    p_parent_id INTEGER DEFAULT NULL,
    p_start_date DATE DEFAULT NULL,
    p_end_date DATE DEFAULT NULL,
    p_rating SMALLINT DEFAULT NULL
)
    RETURNS TABLE(
                     review_id INTEGER,
                     parent_id INTEGER,
                     rating SMALLINT,
                     comments TEXT,
                     created_date TIMESTAMP WITHOUT TIME ZONE
                 ) AS $$
BEGIN
    RETURN QUERY
        SELECT r.review_id,
               r.parent_id,
               r.rating,
               r.comments,
               r.created_date
        FROM reviews r
        WHERE (p_parent_id IS NULL OR r.parent_id = p_parent_id)
          AND (p_start_date IS NULL OR r.created_date >= p_start_date)
          AND (p_end_date IS NULL OR r.created_date <= p_end_date)
          AND (p_rating IS NULL OR r.rating = p_rating);
END;
$$ LANGUAGE plpgsql;

-- Получить статистику популярности категорий блюд
CREATE OR REPLACE FUNCTION get_subscriptions_stats_by_dish_type(
    p_start_date DATE,
    p_end_date DATE
)
    RETURNS TABLE(
                     dish_type_id INTEGER,
                     dish_type_name VARCHAR,
                     subscription_count INTEGER
                 ) AS $$
BEGIN
    RETURN QUERY
        SELECT dt.type_id, dt.name, COUNT(*) AS subscription_count
        FROM dish_type dt
                 JOIN subscription_plan sp ON dt.type_id = sp.dish_type_id
                 JOIN subscription s ON s.plan_id = sp.plan_id
        WHERE s.start_date >= p_start_date AND s.end_date <= p_end_date
        GROUP BY dt.type_id, dt.name
        ORDER BY subscription_count DESC;
END;
$$ LANGUAGE plpgsql;

-- Получить статстику популярности
CREATE OR REPLACE FUNCTION get_dish_count_by_orders(
    p_start_date DATE,
    p_end_date DATE
)
    RETURNS TABLE(
                     dish_id INTEGER,
                     dish_count INTEGER
                 ) AS $$
BEGIN
    RETURN QUERY
        SELECT od.dish_id, SUM(od.quantity) AS dish_count
        FROM order_dishes od
                 JOIN customer_order co ON od.order_id = co.order_id
        WHERE co.delivery_date BETWEEN p_start_date AND p_end_date
        GROUP BY od.dish_id
        ORDER BY dish_count DESC;
END;
$$ LANGUAGE plpgsql;

-- Получить среднюю оценку от каждого родителя
CREATE OR REPLACE FUNCTION get_average_rating_by_parent()
    RETURNS TABLE(
                     parent_id INTEGER,
                     average_rating DECIMAL
                 ) AS $$
BEGIN
    RETURN QUERY
        SELECT r.parent_id, AVG(r.rating)::DECIMAL(10,2) AS average_rating
        FROM reviews r
        GROUP BY r.parent_id;
END;
$$ LANGUAGE plpgsql;