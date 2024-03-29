package com.vizient.vizteamsserver.services;

import com.vizient.vizteamsserver.models.Member;
import com.vizient.vizteamsserver.models.MemberHistory;
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

    @Autowired
    MemberHistoryService memberHistoryService;

    public MemberResponse generateMemberFromRequest(MemberRequest request) {
        Member member = new Member();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setTitle(request.getTitle());
        member.setPathToPhoto(request.getPathToPhoto());
        member.setTeamId(request.getTeam());
        Member savedMember = memberRepository.save(member);

        memberHistoryService.addRecord(member);

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

        if (memberRequest.getTeam() != null) {
            memberById.setTeamId(memberRequest.getTeam());
        }

        memberHistoryService.updateHistory(memberById);

        Member savedMember = memberRepository.save(memberById);
        return generateMemberResponseFromMember(savedMember);

    }

    public MemberResponse generateMemberResponseFromMember(Member member) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setId(member.getId());
        if (member.getTeamId() != null) {
            memberResponse.setTeamId(member.getTeamId());
        }
        memberResponse.setFirstName(member.getFirstName());
        memberResponse.setLastName(member.getLastName());
        memberResponse.setPathToPhoto(member.getPathToPhoto());
        memberResponse.setTitle(member.getTitle());
        return memberResponse;
    }

    public String deleteMember(Long id) {
        memberHistoryService.deleteByMemberId(id);
        Member member = memberRepository.getOne(id);
        if (member != null) {
            memberRepository.delete(member);
            return "Member deleted";
        } else {
            return "No member found";
        }
    }
}
