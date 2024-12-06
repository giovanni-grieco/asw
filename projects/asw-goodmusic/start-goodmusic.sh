#!/bin/bash

# Script per avviare l'applicazione GoodMusic

echo Running GOODMUSIC 

docker run -d --hostname localhost --name asw-consul --network host docker.io/hashicorp/consul
docker run -d --hostname localhost --name recensioni-DB -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=recensioni -d postgres
docker run -d --hostname localhost --name connessioni-DB -p 5433:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=connessioni -d postgres
docker run -d --hostname localhost --name recensioni-seguite-DB -p 5434:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=recensioni-seguite -d postgres


sleep 4

LOGS_FOLDER='logs/'
mkdir -p $LOGS_FOLDER

RECENSIONI_LOGS=$LOGS_FOLDER'recensioni.logs'
RECENSIONI_SEGUITE_LOGS=$LOGS_FOLDER'recensioni-seguite.logs'
CONNESSIONI_LOGS=$LOGS_FOLDER'connessioni.logs'
API_GATEWAY_LOGS=$LOGS_FOLDER'api-gateway.logs'

echo Starting recensioni
java -Xms64m -Xmx128m -jar recensioni/build/libs/recensioni.jar >> $RECENSIONI_LOGS &
echo recensioni logs can be found in
java -Xms64m -Xmx128m -jar connessioni/build/libs/connessioni.jar >> $CONNESSIONI_LOGS &
java -Xms64m -Xmx128m -jar recensioni-seguite/build/libs/recensioni-seguite.jar >> $RECENSIONI_SEGUITE_LOGS &
java -Xms64m -Xmx128m -jar api-gateway/build/libs/api-gateway.jar >> $API_GATEWAY_LOGS &