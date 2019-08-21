package com.vizient.vizteamsserver.interfaces;

import com.vizient.vizteamsserver.requests.MemberRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface MemberInterface {
    @PostMapping(path = "member")
    ResponseEntity<?> createMember(@RequestBody MemberRequest member);

    @GetMapping(path = "member")
    ResponseEntity<?> getAllMembers();

    @GetMapping(path = "member/{id}")
    ResponseEntity<?> getMemberById(@PathVariable Long id);

    @PutMapping(path = "member/{id}")
    ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody MemberRequest memberRequest);

    @DeleteMapping(path = "member/{id}")
    ResponseEntity<?> deleteMember(@PathVariable Long id);
}
