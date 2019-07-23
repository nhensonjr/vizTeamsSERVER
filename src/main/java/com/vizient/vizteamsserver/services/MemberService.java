package com.vizient.vizteamsserver.services;

import com.vizient.vizteamsserver.models.Member;
import com.vizient.vizteamsserver.repositories.MemberRepository;
import com.vizient.vizteamsserver.requests.MemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Member generateMemberFromRequest(MemberRequest request) {
        Member member = new Member();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setTitle(request.getTitle());
        member.setPathToPhoto(request.getPathToPhoto());
        memberRepository.save(member);
        return member;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
