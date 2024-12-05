#!/bin/bash

echo Halting GOODMUSIC   

pkill -f 'recensioni-seguite.jar'
pkill -f 'recensioni.jar'
pkill -f 'connessioni.jar'
pkill -f 'api-gateway.jar'

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