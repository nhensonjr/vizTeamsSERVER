package com.vizient.vizteamsserver.controllers;

import com.vizient.vizteamsserver.interfaces.TeamInterface;
import com.vizient.vizteamsserver.models.Team;
import com.vizient.vizteamsserver.requests.TeamRequest;
import com.vizient.vizteamsserver.services.MemberHistoryService;
import com.vizient.vizteamsserver.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController implements TeamInterface {

    private MemberHistoryService memberHistoryService;
    private TeamService teamService;

    public TeamController(
        TeamService teamService,
        MemberHistoryService memberHistoryService
    ) {
        this.teamService = teamService;
        this.memberHistoryService = memberHistoryService;
    }

    @Override
    public ResponseEntity<?> createTeam(@Validated @RequestBody TeamRequest teamRequest) {
        try {
            Team savedTeam = teamService.generateTeamFromRequest(teamRequest);
            return new ResponseEntity<>(
                savedTeam,
                HttpStatus.CREATED
            );
        } catch(Exception e) {
            return new ResponseEntity<>(
                "Failed to create team: " + e.getMessage(),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    public ResponseEntity<?> getAllTeams() {
        try {
            return new ResponseEntity<>(
                teamService.getAllTeams(),
                HttpStatus.OK
            );
        } catch(Exception e) {
            return new ResponseEntity<>(
                e,
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public ResponseEntity<?> updateTeam(Team team) {
        try {
            return new ResponseEntity<>(
                teamService.updateTeam(team),
                HttpStatus.OK
            );
        } catch(Exception e) {
            return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    public ResponseEntity<?> deleteTeam(Long id) {
        try {
            memberHistoryService.deleteByTeamId(id);
            String message = teamService.deleteTeam(id);
            if(message.equals("Team Deleted")) {
                return new ResponseEntity<>(
                    message,
                    HttpStatus.ACCEPTED
                );
            } else {
                return new ResponseEntity<>(
                    message,
                    HttpStatus.NO_CONTENT
                );
            }
        } catch(Exception e) {
            return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
            );
        }
    }
}
