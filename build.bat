docker build -t clients-rest-api:1.0 clients\
docker build -t orders-rest-api:1.0 orders\

docker-compose rm tinkoffkotlincourse -f

docker-compose build
docker-compose up --detach