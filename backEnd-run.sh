cd BackEnd/service/docker

sudo docker-compose down

sudo docker-compose up -d

cd ..

mvn clean package

java -jar target/service*.jar

cd docker/

sudo docker-compose down
