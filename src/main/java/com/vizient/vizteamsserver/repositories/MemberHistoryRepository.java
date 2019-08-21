package com.vizient.vizteamsserver.repositories;

import com.vizient.vizteamsserver.models.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
    public List<MemberHistory> findAllByMemberId(Long id);
}
