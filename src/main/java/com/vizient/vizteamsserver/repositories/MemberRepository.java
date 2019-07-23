package com.vizient.vizteamsserver.repositories;

import com.vizient.vizteamsserver.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
