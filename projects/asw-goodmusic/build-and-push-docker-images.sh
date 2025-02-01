#!/bin/bash

# per creare le immagini docker dell'applicazione e salvarle su docker hub 

# prerequisito: gradle build
#               docker login su docker hub 

# docker compose build
docker buildx bake 

# docker compose push
docker buildx bake --push 

