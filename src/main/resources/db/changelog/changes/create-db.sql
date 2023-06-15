CREATE SEQUENCE IF NOT EXISTS public.user_id_seq INCREMENT 1 START 2 MINVALUE 1;
CREATE SEQUENCE IF NOT EXISTS public.role_seq INCREMENT 1 START 3 MINVALUE 1;

CREATE TABLE IF NOT EXISTS roles (
      id BIGINT primary key NOT NULL,
      role_name VARCHAR(255)
    );

INSERT INTO roles VALUES(1, 'USER');
INSERT INTO roles VALUES(2, 'ADMIN');

CREATE TABLE IF NOT EXISTS users (
     id BIGINT NOT NULL PRIMARY KEY,
     username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    status VARCHAR(255),
    created_at VARCHAR(255)
    );

INSERT INTO users VALUES(1, 'admin', '$2a$12$DLt4jhFdOFoHLphnVgUQTO8dJ1yWVoaNrSnFowaZeGF4iGFfTtoEW', 'bob', 'bobov', 'ACTIVE', '25-05-2023');

CREATE TABLE IF NOT EXISTS users_roles (
    user_id BIGINT REFERENCES users (id),
    role_id BIGINT REFERENCES roles (id)
    );

INSERT INTO users_roles VALUES(1, 2);
