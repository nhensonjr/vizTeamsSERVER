CREATE TABLE member (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    path_to_photo VARCHAR(255) NOT NULL,
    modified_ts timestamp,
    create_ts timestamp CONSTRAINT DF_team_create_ts DEFAULT (clock_timestamp())
);
