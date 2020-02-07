#!/bin/sh

echo "Starting images"

echo "Starting Spring Boot microservice images"
docker run -d -p 8081:8081 lsr-songs-service
docker run -d -p 8082:8082 lsr-user-service 
docker run -d -p 8083:8083 lsr-vote-service


echo "Starting Angular UI"
docker run -d -p 80:80 lsr-ui

echo "Done starting"