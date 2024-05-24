#!/bin/bash

# Stop and remove containers, networks, images, and volumes
sh staging-stop.sh
git pull
docker compose -f docker-compose-staging.yml up --build
