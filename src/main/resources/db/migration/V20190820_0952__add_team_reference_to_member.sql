alter table member add team_id INT CONSTRAINT FK_member_to_team FOREIGN KEY (team_id) REFERENCES team(id)
