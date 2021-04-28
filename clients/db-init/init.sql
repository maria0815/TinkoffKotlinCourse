CREATE SCHEMA clients_schema;
GRANT ALL ON SCHEMA clients_schema TO docker_user;
ALTER USER docker_user SET search_path TO clients_schema;

DROP TABLE IF EXISTS clients_schema.client CASCADE;

CREATE TABLE clients_schema.client
(
    id      SERIAL,
    name    VARCHAR(50) not null,
    address VARCHAR(50) not null,
    PRIMARY KEY (id)
);

INSERT INTO clients_schema.client (name, address)
VALUES ('Клиент 1', 'Адресс клиента 1'),
       ('Клиент 2', 'Адресс клиента 2'),
       ('Клиент 3', 'Адресс клиента 3'),
       ('Клиент 4', 'Адресс клиента 4'),
       ('Клиент 5', 'Адресс клиента 5'),
       ('Клиент 6', 'Адресс клиента 6');