package com.vizient.vizteamsserver.interfaces;

import com.vizient.vizteamsserver.models.Team;
import com.vizient.vizteamsserver.requests.TeamRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TeamInterface {
    @PostMapping(path = "team")
    ResponseEntity<?> createTeam(@RequestBody TeamRequest teamRequest);

    @GetMapping(path = "team")
    ResponseEntity<?> getAllTeams();

    @PutMapping(path = "team/{id}")
    ResponseEntity<?> updateTeam(@RequestBody Team team);

    @DeleteMapping(path = "team/{id}")
    ResponseEntity<?> deleteTeam(@PathVariable Long id);
}
