CREATE TABLE IF NOT EXISTS post (
id INT,
user_id INT,
content TEXT,
category_id INT,
image VARCHAR(255),
date DATE,
location VARCHAR(255),
provider VARCHAR(255),
status VARCHAR(50),
time TIME,
title VARCHAR(255)
);