package com.vizient.vizteamsserver.services;

import com.vizient.vizteamsserver.models.Member;
import com.vizient.vizteamsserver.models.Team;
import com.vizient.vizteamsserver.repositories.MemberRepository;
import com.vizient.vizteamsserver.repositories.TeamRepository;
import com.vizient.vizteamsserver.requests.MemberRequest;
import com.vizient.vizteamsserver.responses.MemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    public MemberResponse generateMemberFromRequest(MemberRequest request) {
        Member member = new Member();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setTitle(request.getTitle());
        member.setPathToPhoto(request.getPathToPhoto());
        member.setTeam(teamRepository.getOne(request.getTeam()));
        Member savedMember = memberRepository.save(member);

        return generateMemberResponseFromMember(savedMember);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.getOne(id);
    }

    public MemberResponse updateMember(Member memberById, MemberRequest memberRequest) {
        memberById.setFirstName(memberRequest.getFirstName());
        memberById.setLastName(memberRequest.getLastName());
        memberById.setPathToPhoto(memberRequest.getPathToPhoto());
        memberById.setTitle(memberRequest.getTitle());

        Team team = teamRepository.getOne(memberRequest.getTeam());
        memberById.setTeam(team);

        Member savedMember = memberRepository.save(memberById);
        return generateMemberResponseFromMember(savedMember);

    }

    public MemberResponse generateMemberResponseFromMember(Member member) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setId(member.getId());
        memberResponse.setTeamId(member.getTeam().getId());
        memberResponse.setFirstName(member.getFirstName());
        memberResponse.setLastName(member.getLastName());
        memberResponse.setPathToPhoto(member.getPathToPhoto());
        memberResponse.setTitle(member.getTitle());
        return memberResponse;
    }

    public String deleteMember(Long id) {
        Member member = memberRepository.getOne(id);
        if (member != null) {
            memberRepository.delete(member);
            return "Member deleted";
        } else {
            return "No member found";
        }
    }
}
