-- =========================
-- INSERT CATEGORIES
-- =========================

insert into category (id, description, name)
values
    (1, 'Electronic devices and accessories', 'Electronics'),
    (2, 'Books and educational materials', 'Books'),
    (3, 'Sports equipment and fitness items', 'Sports'),
    (4, 'Home appliances and furniture', 'Home');



-- =========================
-- INSERT PRODUCTS
-- =========================

insert into product
(id, description, name, available_quantity, price, category_id)
values
    (1, 'Gaming laptop with 16GB RAM', 'Laptop', 10, 1200.00, 1),

    (2, 'Wireless noise cancelling headphones',
     'Headphones', 25, 250.00, 1),

    (3, 'Spring Boot microservices guide',
     'Spring Boot Book', 40, 45.50, 2),

    (4, 'English grammar improvement book',
     'English Grammar Book', 30, 20.00, 2),

    (5, 'Professional football',
     'Football', 50, 35.99, 3),

    (6, 'Adjustable dumbbells set',
     'Dumbbells', 15, 89.90, 3),

    (7, 'Wooden office desk',
     'Office Desk', 5, 300.00, 4),

    (8, 'Modern LED lamp',
     'LED Lamp', 18, 49.99, 4);