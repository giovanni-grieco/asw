#!/bin/bash

echo 'Starting goodmusic'

kubectl apply -f rbac-authorizations.yaml

kubectl create namespace goodmusic
kubectl apply -f goodmusic-application.yaml -n goodmusic

# kubectl rollout status deployment/sentence -n sentence

