CREATE TABLE IF NOT EXISTS reviews (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         user_id INT,
                         post_id INT,
                         rating INT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
