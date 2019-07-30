package com.vizient.vizteamsserver.interfaces;

import com.vizient.vizteamsserver.requests.TeamRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TeamInterface {
    @PostMapping(path = "team")
    ResponseEntity<?> createTeam(@RequestBody TeamRequest teamRequest);
}
