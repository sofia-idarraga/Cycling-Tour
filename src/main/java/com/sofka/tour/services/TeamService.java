package com.sofka.tour.services;

import com.sofka.tour.domain.Team;
import com.sofka.tour.repository.TeamRepository;
import com.sofka.tour.services.interfaces.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private TeamRepository teamRepository;



    @Override
    public Team registerTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team findTeamByCode(String code) {
        return teamRepository.findTeamByTeamCode(code);
    }

    @Override
    public List<Team> findTeamsByCountry(String country) {
        return teamRepository.findTeamsByCountry(country);
    }

    @Override
    public boolean deleteTeam(String code) {
        Team team = teamRepository.findTeamByTeamCode(code);
        if(team == null){
            return false;
        }
        teamRepository.deleteById(team.getId());
        return true;
    }

    @Override
    public Team updateTeam(Team team) {
        Team teamToUpdate = teamRepository.findTeamByTeamCode(team.getTeamCode());
        if(teamToUpdate != null){
            team.setId(teamToUpdate.getId());
            team.setCyclists(teamToUpdate.getCyclists());
            return team;
        }
        return null;
    }
}
