#!/usr/bin/env bash

docker stop vizteams
docker rm vizteams

docker run \
    -e 'ACCEPT_EULA=Y' \
    -e 'SA_PASSWORD=password1E!' \
    -p 1437:1433 \
    --name vizteams \
    -d \
    --restart unless-stopped \
    mcr.microsoft.com/mssql/server:2017-latest-ubuntu

sleep 15
docker cp vizteamssetup.sql vizteams:/vizteamssetup.sql
sleep 15
winpty docker exec -it vizteams sh -c "./opt/mssql-tools/bin/sqlcmd -s localhost -U SA -P password1E! -i vizteamssetup.sql"
