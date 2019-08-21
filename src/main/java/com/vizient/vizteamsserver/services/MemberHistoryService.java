package com.vizient.vizteamsserver.services;

import com.vizient.vizteamsserver.models.Member;
import com.vizient.vizteamsserver.models.MemberHistory;
import com.vizient.vizteamsserver.repositories.MemberHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberHistoryService {

    private MemberHistoryRepository memberHistoryRepository;

    public MemberHistoryService(MemberHistoryRepository memberHistoryRepository) {
        this.memberHistoryRepository = memberHistoryRepository;
    }

    void addRecord(Member member) {
        MemberHistory history = new MemberHistory();
        history.setMemberId(member.getId());
        history.setTeamId(member.getTeam().getId());
        history.setStartedOnTeam(OffsetDateTime.now(ZoneOffset.UTC));
        memberHistoryRepository.save(history);
    }

    void updateHistory(Member member) {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        List<MemberHistory> histories = memberHistoryRepository.findAllByMemberId(member.getId());
        List<MemberHistory> historiesWithNoLeftDate = histories.stream().filter(memberHistory -> memberHistory.getLeftTeam() == null).collect(Collectors.toList());
        List<MemberHistory> isRecentlyOffTeam = histories.stream().filter(memberHistory -> memberHistory.getLeftTeam() != null && memberHistory.getLeftTeam().plusHours(24).isAfter(now)).collect(Collectors.toList());
        if (isRecentlyOffTeam.size() > 0 && member.getTeam().getId().equals(isRecentlyOffTeam.get(0).getTeamId())) {
            isRecentlyOffTeam.get(0).setLeftTeam(null);
            memberHistoryRepository.save(isRecentlyOffTeam.get(0));
            memberHistoryRepository.deleteInBatch(historiesWithNoLeftDate);
            return;
        }
        if (historiesWithNoLeftDate.size() > 0) {
            MemberHistory recentHistory = historiesWithNoLeftDate.get(0);
            if (recentHistory.getTeamId().equals(member.getTeam().getId())) {
                return;
            }

            if (!recentHistory.getStartedOnTeam().plusHours(24).isAfter(now)) {
                recentHistory.setLeftTeam(now);
                memberHistoryRepository.save(recentHistory);
                addRecord(member);
            } else {
                recentHistory.setTeamId(member.getTeam().getId());
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
}
