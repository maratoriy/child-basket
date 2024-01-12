INSERT INTO allergen (name) VALUES
                                ('Peanuts'),
                                ('Milk'),
                                ('Eggs'),
                                ('Fish');

INSERT INTO ingredient (name) VALUES
                                  ('Carrot'),
                                  ('Chicken'),
                                  ('Rice'),
                                  ('Broccoli');

INSERT INTO dish_type (name, description) VALUES
                                              ('Puree', 'Pureed baby food'),
                                              ('Chunks', 'Finger foods for toddlers'),
                                              ('Snacks', 'Healthy snacks for kids');

INSERT INTO dish (dish_type_id, name, description, calories, proteins, fats, carbohydrates) VALUES
                                                                                                (1, 'Carrot Puree', 'Smooth puree made from carrots', 50, 1, 0, 12),
                                                                                                (2, 'Chicken Chunks', 'Small chunks of chicken for toddlers', 80, 15, 3, 0),
                                                                                                (3, 'Rice Snack', 'Healthy rice-based snack', 60, 1, 1, 13);

INSERT INTO subscription_plan (dish_type_id, name, description, price, meal_frequency, meal_per_order) VALUES
                                                                                                           (1, 'Basic Plan', 'Includes pureed baby food', 30, 2, 3),
                                                                                                           (2, 'Toddler Plan', 'Finger foods for toddlers', 40, 3, 2),
                                                                                                           (3, 'Snack Plan', 'Healthy snacks for kids', 25, 1, 1);

INSERT INTO delivery_status (status) VALUES
                                         ('Pending'),
                                         ('In Transit'),
                                         ('Delivered');

INSERT INTO dish_ingredients (dish_id, ingredient_id) VALUES
                                                          (1, 1),
                                                          (2, 2),
                                                          (3, 3);

INSERT INTO dish_allergens (dish_id, allergen_id) VALUES
                                                      (1, 1),
                                                      (2, 2),
                                                      (3, 3);
