package com.vizient.vizteamsserver.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vizient.vizteamsserver.requests.MemberRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface MemberInterface {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "member")
    ResponseEntity<?> createMember(@RequestBody MemberRequest member) throws JsonProcessingException;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "member")
    ResponseEntity<?> getAllMembers() throws JsonProcessingException;
}
