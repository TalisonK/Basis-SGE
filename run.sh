#!/bin/bash

gnome-terminal -- /bin/sh -c '\
cd frontend

npm i && npm start;sleep 604800'

gnome-terminal -- /bin/sh -c '\

cd BackEnd/service/docker

sudo docker-compose down

sudo docker-compose up -d

cd ..

mvn clean package

java -jar target/service*.jar

cd docker/

sudo docker-compose down;sleep 604800'

sleep 5
exit
