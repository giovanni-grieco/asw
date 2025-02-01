#!/bin/bash

echo 'Halting goodmusic'

kubectl delete -f goodmusic-application.yaml -n goodmusic
kubectl delete namespace goodmusic

kubectl delete -f rbac-authorizations.yaml

