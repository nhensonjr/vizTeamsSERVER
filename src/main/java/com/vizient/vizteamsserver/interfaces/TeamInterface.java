package com.vizient.vizteamsserver.interfaces;

import com.vizient.vizteamsserver.requests.TeamRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TeamInterface {
    @PostMapping(path = "team")
    ResponseEntity<?> createTeam(@RequestBody TeamRequest teamRequest);

    @GetMapping(path = "team")
    ResponseEntity<?> getAllTeams();

    @DeleteMapping
    ResponseEntity<?> deleteTeam(@PathVariable Long id);
}
