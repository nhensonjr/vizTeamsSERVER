package com.vizient.vizteamsserver.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface MemberHistoryInterface {
    @GetMapping(path = "history/{id}")
    ResponseEntity<?> getAllForMember(@PathVariable Long id);

    @GetMapping(path = "history-team/{id}")
    ResponseEntity<?> getAllForTeam(@PathVariable Long id);
}
