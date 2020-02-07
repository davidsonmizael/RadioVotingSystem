#!/bin/sh

echo "Building images"

echo "Building Spring Boot microservice images"
docker build -t lsr-songs-service songs-service/
docker build -t lsr-user-service user-service/
docker build -t lsr-vote-service vote-service/

echo "Building Angular UI"
docker build -t lsr-ui lsr-ui/

echo "Done building"