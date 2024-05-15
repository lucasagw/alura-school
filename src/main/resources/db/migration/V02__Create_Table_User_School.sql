CREATE TABLE  IF NOT EXISTS user_school (
                             username VARCHAR(20) NOT NULL,
                             email VARCHAR(100) NOT NULL,
                             name VARCHAR(200) NOT NULL,
                             password VARCHAR(255) NOT NULL,
                             created_at DATE NOT NULL,
                             PRIMARY KEY (username, email)
);