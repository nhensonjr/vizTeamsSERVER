package com.vizient.vizteamsserver.interfaces;

import com.vizient.vizteamsserver.requests.MemberRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface MemberInterface {
    @PostMapping(path = "member")
    ResponseEntity<?> createMember(@RequestBody MemberRequest member);

    @GetMapping(path = "member")
    ResponseEntity<?> getAllMembers();

    @GetMapping(path = "member/{id}")
    ResponseEntity<?> getMemberById(@PathVariable Long id);
}
