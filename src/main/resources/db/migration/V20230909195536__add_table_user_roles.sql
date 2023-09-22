CREATE TABLE user_roles
(
    user_id BIGINT                NOT NULL,
    role    VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_curso PRIMARY KEY (user_id, role)
);