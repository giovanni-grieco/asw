#!/bin/bash

echo Halting GOODMUSIC   

pkill -f 'recensioni-seguite.jar'
pkill -f 'recensioni.jar'
pkill -f 'connessioni.jar'
pkill -f 'api-gateway.jar'

cd logs &&\
rm -f recensioni.logs &&\
rm -f recensioni-seguite.logs &&\
rm -f connessioni.logs &&\
rm -f api-gateway.logs &&\
cd ..

sleep 4 

docker stop asw-consul 
docker rm asw-consul 
docker stop recensioni-DB
docker rm recensioni-DB
docker stop connessioni-DB
docker rm connessioni-DB
docker stop recensioni-seguite-DB
docker rm recensioni-seguite-DB
docker compose down