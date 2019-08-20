CREATE TABLE team (
    id INT NOT NULL identity(1, 1),
    name VARCHAR(255) NOT NULL,
    description VARCHAR(max) NOT NULL,
    modified_ts DATETIMEOFFSET,
    create_ts DATETIMEOFFSET CONSTRAINT DF_team_create_ts DEFAULT (GETUTCDATE()),
    CONSTRAINT PK__team PRIMARY KEY (id)
);
GO
