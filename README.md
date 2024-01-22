____________________________________________________________________

#How to restart backend server
docker compose build backend
docker compose down backend
docker compose up -d backend

_____________________________________________________________________

#For up docker containers
docker compose up -d

#For stop and remove containers
docker compose down

#For stop only
docker compose stop

#For see containers
docker compose ps
#Or all containers
docker ps -a

#Check container logs
docker compose logs -f 

_____________________________________________________________________

#Connect to server with SSH
ssh root@87.249.49.62
Password: tFrHy+F.3k--UY

#Connect to DB
psql -U admin -h 87.249.49.62 -d terrestrial_tutor
Password: tErRrEsTrIaLtUtOr
