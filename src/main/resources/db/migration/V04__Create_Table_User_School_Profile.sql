
CREATE INDEX idx_email ON user_school (email);

CREATE TABLE IF NOT EXISTS user_school_profile (
    user_username VARCHAR(20) NOT NULL,
    user_email VARCHAR(100) NOT NULL,
    profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_username, user_email, profile_id),
    FOREIGN KEY (user_username, user_email) REFERENCES user_school(username, email),
    FOREIGN KEY (profile_id) REFERENCES profile(id)
    );