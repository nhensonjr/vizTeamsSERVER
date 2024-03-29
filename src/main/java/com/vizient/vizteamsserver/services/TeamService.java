package com.vizient.vizteamsserver.services;

import com.vizient.vizteamsserver.models.Team;
import com.vizient.vizteamsserver.repositories.MemberRepository;
import com.vizient.vizteamsserver.repositories.TeamRepository;
import com.vizient.vizteamsserver.requests.TeamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MemberRepository memberRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team generateTeamFromRequest(TeamRequest teamRequest) {
        Team team = new Team();
        team.setName(teamRequest.getName());
        team.setDescription(teamRequest.getDescription());
        team.setMembers(null);
        teamRepository.save(team);
        return team;
    }

    public String deleteTeam(Long id) {
        Team team = teamRepository.getOne(id);

        if (team != null) {
            team.getMembers().forEach(member -> {
                member.setTeamId(null);
            });
            teamRepository.delete(team);
            return "Team Deleted";
        } else {
            return "No Team Found";
        }
    }

    public Team updateTeam(Team team) {
       return teamRepository.save(team);
    }
}
