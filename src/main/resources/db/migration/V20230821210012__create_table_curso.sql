CREATE TABLE curso
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    nome      VARCHAR(255)          NULL,
    categoria VARCHAR(255)          NULL,
    CONSTRAINT pk_curso PRIMARY KEY (id)
);