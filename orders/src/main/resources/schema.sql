USE master;
IF EXISTS(SELECT *
          FROM sys.databases
          WHERE name = 'orders_db')
    BEGIN
        ALTER DATABASE orders_db SET SINGLE_USER WITH ROLLBACK IMMEDIATE
        USE master
        DROP DATABASE IF EXISTS orders_db
    END;
CREATE DATABASE orders_db;

USE orders_db;

DROP TABLE IF EXISTS "Order";

CREATE TABLE "Order"
(
    id       int IDENTITY (1,1) PRIMARY KEY,
    date     date not null,
    clientId int  not null
);