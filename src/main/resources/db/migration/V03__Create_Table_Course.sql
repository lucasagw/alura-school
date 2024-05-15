CREATE TABLE  IF NOT EXISTS course (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(220) NOT NULL,
                        code VARCHAR(10) UNIQUE NOT NULL,
                        instructor_username VARCHAR(200) NOT NULL,
                        instructor_email VARCHAR(100) NOT NULL,
                        description VARCHAR(350) NOT NULL,
                        status BOOLEAN NOT NULL,
                        created_at DATE NOT NULL,
                        deactivated_at DATE,
                        PRIMARY KEY (id),
                        FOREIGN KEY (instructor_username, instructor_email) REFERENCES user_school(username, email)
);