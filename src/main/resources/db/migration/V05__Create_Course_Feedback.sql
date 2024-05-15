CREATE TABLE  IF NOT EXISTS course_feedback (
                                 id BIGINT NOT NULL AUTO_INCREMENT,
                                 course_id BIGINT NOT NULL,
                                 student_username VARCHAR(20) NOT NULL,
                                 student_email VARCHAR(100) NOT NULL,
                                 comment VARCHAR(300) NOT NULL,
                                 rating INT NOT NULL,
                                 created_at DATE NOT NULL,
                                 PRIMARY KEY (id),
                                 FOREIGN KEY (course_id) REFERENCES course(id),
                                 FOREIGN KEY (student_username, student_email) REFERENCES user_school(username, email)
);