CREATE TABLE member_history (
    id INT NOT NULL identity(1, 1),
    team_id INT not null CONSTRAINT FK_member_history_to_team FOREIGN KEY (team_id) REFERENCES team(id),
    member_id INT not null CONSTRAINT FK_member_history_to_member FOREIGN KEY (member_id) REFERENCES member(id),
    started_on_team DATETIMEOFFSET not null CONSTRAINT DF_member_history_started_on_team DEFAULT (GETUTCDATE()),
    left_team DATETIMEOFFSET,
    modified_ts DATETIMEOFFSET,
    create_ts DATETIMEOFFSET CONSTRAINT DF_member_history_create_ts DEFAULT (GETUTCDATE()),
    CONSTRAINT PK__member_history PRIMARY KEY (id)
);
GO
