DROP TABLE IF EXISTS "Order";

CREATE TABLE "Order"
(
    id       int IDENTITY (1,1) PRIMARY KEY,
    date     date not null,
    clientId int  not null
);