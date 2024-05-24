#!/bin/bash

# Stop and remove containers, networks, images, and volumes
docker compose -f docker-compose-staging.yml down
docker system prune -f
