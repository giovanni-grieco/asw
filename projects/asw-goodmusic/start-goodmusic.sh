#!/bin/bash

# Script per avviare l'applicazione GoodMusic

echo Running GOODMUSIC 

docker run -d --hostname localhost --name asw-consul --network host docker.io/hashicorp/consul
docker run -d --hostname localhost --name recensioni-DB -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=recensioni -d postgres
docker run -d --hostname localhost --name connessioni-DB -p 5433:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=connessioni -d postgres
docker run -d --hostname localhost --name recensioni-seguite-DB -p 5434:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=recensioni-seguite -d postgres
docker compose up -d

KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic recensioni --replication-factor 1 --partitions 4
docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic connessioni --replication-factor 1 --partitions 4


sleep 4

java -Xms64m -Xmx128m -jar recensioni/build/libs/recensioni.jar &
java -Xms64m -Xmx128m -jar connessioni/build/libs/connessioni.jar &
java -Xms64m -Xmx128m -jar recensioni-seguite/build/libs/recensioni-seguite.jar &
java -Xms64m -Xmx128m -jar api-gateway/build/libs/api-gateway.jar &