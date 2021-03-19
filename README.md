# docker-compose-example
# Prerequisites
1) docker installed. https://docs.docker.com/engine/install/ubuntu/
2) docker-compose installed. https://docs.docker.com/compose/install/

# Quickstart
1) Clone repository.
2) Enter cloned folder via cmd(cd docker-compose-example).
3) Run docker-compose up.

# Data safe
Component volumes mounted in volumes folder in project root so your data kept safe when components aren't working.

# Customization
Components are getting configs from env folder.

# Helpful commands
docker-compose ps - list running containers. Also shows containers 'health' based on healthcheck from docker-compose.yml

docker-compose logs -f {container_name} - replace {container_name} with the name of running container and watch logs from it.

Other
Each Java application is built via multy-stage build. Build process described in Dockerfile in corresponding app root folder
