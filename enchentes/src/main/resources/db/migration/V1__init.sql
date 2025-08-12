CREATE TABLE roles
(
    id         BIGSERIAL PRIMARY KEY,
    rolename   VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    role_id    BIGINT REFERENCES roles (id),
    username   VARCHAR(120) UNIQUE NOT NULL,
    password   VARCHAR(120)        NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);

INSERT INTO roles (rolename)
VALUES ('USUARIO'),
       ('ADMIN');