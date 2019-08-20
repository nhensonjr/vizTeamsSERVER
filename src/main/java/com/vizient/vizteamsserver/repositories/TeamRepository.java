package com.vizient.vizteamsserver.repositories;

import com.vizient.vizteamsserver.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
