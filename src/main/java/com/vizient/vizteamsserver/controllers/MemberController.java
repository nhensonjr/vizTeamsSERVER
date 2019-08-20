package com.vizient.vizteamsserver.controllers;

import com.vizient.vizteamsserver.interfaces.MemberInterface;
import com.vizient.vizteamsserver.models.Member;
import com.vizient.vizteamsserver.requests.MemberRequest;
import com.vizient.vizteamsserver.responses.MemberResponse;
import com.vizient.vizteamsserver.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class MemberController implements MemberInterface {

    @Autowired
    MemberService memberService;

    @Override
    public ResponseEntity<?> createMember(@Validated @RequestBody MemberRequest request) {
        try {
            MemberResponse savedMember = memberService.generateMemberFromRequest(request);
            return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>("Failed to create member: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAllMembers() {
        try {
            List<MemberResponse> responses = new ArrayList<>();
            memberService.getAllMembers().forEach(member -> {
                responses.add(memberService.generateMemberResponseFromMember(member));
            });
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Failed to find members: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        try {
            Member memberById = memberService.getMemberById(id);
            if (memberById.getId() != null) {
                return new ResponseEntity<>(memberService.generateMemberResponseFromMember(memberById), HttpStatus.OK);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("No member found with id: " + id, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody MemberRequest memberRequest) {
        try {
            Member memberById = memberService.getMemberById(id);
            if (memberById.getId() != null) {
                MemberResponse updatedMemberResponse = memberService.updateMember(memberById, memberRequest);
                return new ResponseEntity<>(updatedMemberResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Member not found for Id: " + id, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        try {
            String message = memberService.deleteMember(id);
            if (message.equals("Member deleted")) {
                return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
