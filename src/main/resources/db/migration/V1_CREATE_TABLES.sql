-- Tabela 'profile'
CREATE TABLE profile (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL,
                      PRIMARY KEY (id)
);

-- Tabela 'user_school'
CREATE TABLE user_school (
                             username VARCHAR(20) NOT NULL,
                             email VARCHAR(255) NOT NULL,
                             name VARCHAR(255) NOT NULL,
                             password VARCHAR(255) NOT NULL,
                             created_at DATE NOT NULL,
                             PRIMARY KEY (username, email)
);

-- Tabela 'user_school_role' (Relacionamento Many-to-Many entre user_school e role)
CREATE TABLE user_school_profile (
                                  user_key_username VARCHAR(20) NOT NULL,
                                  user_key_email VARCHAR(255) NOT NULL,
                                  profile_id BIGINT NOT NULL,
                                  PRIMARY KEY (user_key_username, user_key_email, role_id),
                                  FOREIGN KEY (user_key_username, user_key_email) REFERENCES user_school(username, email),
                                  FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Tabela 'course'
CREATE TABLE course (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(220) NOT NULL,
                        code VARCHAR(10) UNIQUE NOT NULL,
                        instructor_name VARCHAR(20) NOT NULL,
                        instructor_email VARCHAR(120) NOT NULL,
                        description VARCHAR(350) NOT NULL,
                        status BOOLEAN NOT NULL,
                        created_at DATE NOT NULL,
                        deactivated_at DATE,
                        PRIMARY KEY (id),
                        FOREIGN KEY (instructor_name, instructor_email) REFERENCES user_school(username, email)
);

-- Tabela 'course_feedback'
CREATE TABLE course_feedback (
                                 id BIGINT NOT NULL AUTO_INCREMENT,
                                 course_id BIGINT NOT NULL,
                                 student_name VARCHAR(20) NOT NULL,
                                 student_email VARCHAR(255) NOT NULL,
                                 comment VARCHAR(255) NOT NULL,
                                 rating INT NOT NULL,
                                 created_at DATE NOT NULL,
                                 PRIMARY KEY (id),
                                 FOREIGN KEY (course_id) REFERENCES course(id),
                                 FOREIGN KEY (student_name, student_email) REFERENCES user_school(username, email)
);

-- Tabela 'enrollment'
CREATE TABLE enrollment (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            student_name VARCHAR(20) NOT NULL,
                            student_email VARCHAR(255) NOT NULL,
                            course_id BIGINT NOT NULL,
                            created_at DATE NOT NULL,
                            PRIMARY KEY (id),
                            FOREIGN KEY (student_name, student_email) REFERENCES user_school(username, email),
                            FOREIGN KEY (course_id) REFERENCES course(id)
);
