CREATE TABLE  IF NOT EXISTS enrollment (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            student_username VARCHAR(200) NOT NULL,
                            student_email VARCHAR(100) NOT NULL,
                            course_id BIGINT NOT NULL,
                            created_at DATE NOT NULL,
                            PRIMARY KEY (id),
                            FOREIGN KEY (student_username, student_email) REFERENCES user_school(username, email),
                            FOREIGN KEY (course_id) REFERENCES course(id)
);
