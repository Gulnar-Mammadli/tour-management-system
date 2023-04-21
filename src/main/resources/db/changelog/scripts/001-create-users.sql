CREATE TABLE IF NOT EXISTS users (
                       id BIGSERIAL PRIMARY KEY,
                       user_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       phone_number VARCHAR(255) NOT NULL,
                       roles VARCHAR(255),
                       deleted BOOLEAN DEFAULT false,
                       UNIQUE  (user_name),
                       UNIQUE (email)
);
