CREATE TABLE member_history (
    id  SERIAL PRIMARY KEY,
    team_id INT REFERENCES team(id),
    member_id INT REFERENCES member(id),
    started_on_team timestamp not null CONSTRAINT DF_member_history_started_on_team DEFAULT (clock_timestamp()),
    left_team timestamp,
    modified_ts timestamp,
    create_ts timestamp CONSTRAINT DF_member_history_create_ts DEFAULT (clock_timestamp())
);
