DROP TABLE IF EXISTS client CASCADE;

CREATE TABLE client
(
    id      SERIAL,
    name    VARCHAR(50) not null,
    address VARCHAR(50) not null,
    PRIMARY KEY (id)
);