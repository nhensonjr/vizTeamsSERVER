package com.vizient.vizteamsserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vizient.vizteamsserver.interfaces.MemberInterface;
import com.vizient.vizteamsserver.models.Member;
import com.vizient.vizteamsserver.requests.MemberRequest;
import com.vizient.vizteamsserver.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MemberController implements MemberInterface {

    @Autowired
    MemberService memberService;

    @Override
    public ResponseEntity<?> createMember(@Validated @RequestBody MemberRequest request) {
        try {
            Member savedMember = memberService.generateMemberFromRequest(request);
            return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>("Failed to create member: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAllMembers() throws JsonProcessingException {
        try {
            return new ResponseEntity<>(memberService.getAllMembers(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Failed to find members: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
