CREATE TABLE IF NOT EXISTS users (
                       id INT,
                       email VARCHAR(255),
                       password VARCHAR(255),
                       name VARCHAR(255),
                       surname VARCHAR(255),
                       user_role_id INT,
                       location_id INT,
                       phone_number VARCHAR(20)
);