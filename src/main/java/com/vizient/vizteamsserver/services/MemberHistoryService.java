package com.vizient.vizteamsserver.services;

import com.vizient.vizteamsserver.models.Member;
import com.vizient.vizteamsserver.models.MemberHistory;
import com.vizient.vizteamsserver.repositories.MemberHistoryRepository;
import com.vizient.vizteamsserver.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberHistoryService {

    private MemberHistoryRepository memberHistoryRepository;
    private TeamRepository teamRepository;

    public MemberHistoryService(
        MemberHistoryRepository memberHistoryRepository,
        TeamRepository teamRepository
    ) {
        this.memberHistoryRepository = memberHistoryRepository;
        this.teamRepository = teamRepository;
    }

    public List<MemberHistory> getAllByMemberId(Long id) {
        return memberHistoryRepository.findAllByMemberId(id);
    }

    void addRecord(Member member) {
        MemberHistory history = new MemberHistory();
        history.setMemberId(member.getId());
        history.setTeamId(member.getTeamId());
        history.setTeamName(teamRepository.getOne(member.getTeamId()).getName());
        history.setStartedOnTeam(OffsetDateTime.now(ZoneOffset.UTC));
        history.setMemberName(member.getFirstName() + " " + member.getLastName());
        memberHistoryRepository.save(history);
    }

    void updateHistory(Member member) {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        List<MemberHistory> histories = memberHistoryRepository.findAllByMemberId(member.getId());
        List<MemberHistory> historiesWithNoLeftDate = histories.stream().filter(memberHistory -> memberHistory.getLeftTeam() == null).collect(Collectors.toList());
        List<MemberHistory> isRecentlyOffTeam = histories.stream().filter(memberHistory -> memberHistory.getLeftTeam() != null && memberHistory.getLeftTeam().plusHours(24).isAfter(now)).collect(Collectors.toList());
        if (isRecentlyOffTeam.size() > 0 && member.getTeamId().equals(isRecentlyOffTeam.get(0).getTeamId())) {
            isRecentlyOffTeam.get(0).setLeftTeam(null);
            memberHistoryRepository.save(isRecentlyOffTeam.get(0));
            memberHistoryRepository.deleteInBatch(historiesWithNoLeftDate);
            return;
        }
        if (historiesWithNoLeftDate.size() > 0) {
            MemberHistory recentHistory = historiesWithNoLeftDate.get(0);
            if (recentHistory.getTeamId().equals(member.getTeamId())) {
                return;
            }

            if (!recentHistory.getStartedOnTeam().plusHours(24).isAfter(now)) {
                recentHistory.setLeftTeam(now);
                memberHistoryRepository.save(recentHistory);
                addRecord(member);
            } else {
                recentHistory.setTeamId(member.getTeamId());
                recentHistory.setLeftTeam(null);
            }
        } else {
            addRecord(member);
        }

    }

    void deleteByMemberId(Long id) {
        List<MemberHistory> allByMemberId = memberHistoryRepository.findAllByMemberId(id);
        memberHistoryRepository.deleteInBatch(allByMemberId);
    }

    public void deleteByTeamId(Long id) {
        List<MemberHistory> memberHistoryList = memberHistoryRepository.findAllByTeamId(id);
        memberHistoryRepository.deleteInBatch(memberHistoryList);
    }

    public List<MemberHistory> getAllByTeamId(Long id) {
        return memberHistoryRepository.findAllByTeamId(id);
    }
}
