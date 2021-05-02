create table orders
(
    id       SERIAL primary key,
    date     date not null,
    "clientId" int  not null
)