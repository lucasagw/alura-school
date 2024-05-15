
CREATE INDEX idx_email ON user_school (email);

CREATE TABLE IF NOT EXISTS user_school_profile (
                                                   user_key_username VARCHAR(20) NOT NULL,
    user_key_email VARCHAR(100) NOT NULL,
    profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_key_username, user_key_email, profile_id),
    FOREIGN KEY (user_key_username, user_key_email) REFERENCES user_school(username, email),
    FOREIGN KEY (profile_id) REFERENCES profile(id)
    );