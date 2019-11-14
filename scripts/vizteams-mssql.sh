#!/usr/bin/env bash

docker stop vizteams-pg
docker rm vizteams-pg

docker run \
    -e 'ACCEPT_EULA=Y' \
    -e 'POSTGRES_PASSWORD=password1E!' \
    -e 'POSTGRES_DB=dfkv4ejekt9ers' \
    -p 5432:5432 \
    --name vizteams-pg \
    -d \
    --restart unless-stopped \
    postgres

sleep 15
docker cp vizteamssetup.sql vizteams-pg:/vizteamssetup.sql
sleep 15
winpty docker exec -it vizteams-pg sh -c "psql -h localhost -U postgres -f vizteamssetup.sql"
