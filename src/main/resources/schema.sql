CREATE TABLE IF NOT EXISTS "user" (
    id BIGINT NOT NULL AUTO_INCREMENT,
    ip CHAR(40),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS translation_details (
    id BIGINT NOT NULL AUTO_INCREMENT,
    source_text VARCHAR NOT NULL,
    translated_text VARCHAR NOT NULL,
    to_language CHAR(5) NOT NULL,
    source_language CHAR(5) NOT NULL,
    request_date TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);


