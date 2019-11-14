CREATE TABLE team (
    id  SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(10000) NOT NULL,
    modified_ts timestamp,
    create_ts timestamp CONSTRAINT DF_team_create_ts DEFAULT (clock_timestamp())
);
