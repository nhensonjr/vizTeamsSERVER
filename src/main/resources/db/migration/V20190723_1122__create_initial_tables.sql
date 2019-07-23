CREATE TABLE member (
    id INT NOT NULL identity(1, 1),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    path_to_photo VARCHAR(255) NOT NULL,
    modified_ts DATETIMEOFFSET,
    create_ts DATETIMEOFFSET CONSTRAINT DF_member_create_ts DEFAULT (GETUTCDATE()),
    CONSTRAINT PK__member PRIMARY KEY (id)
);
GO
