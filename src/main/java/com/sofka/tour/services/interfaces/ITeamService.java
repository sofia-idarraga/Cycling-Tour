package com.sofka.tour.services.interfaces;

import com.sofka.tour.domain.Team;

import java.util.List;

public interface ITeamService {

    Team registerTeam(Team team);
    List<Team> getAllTeams();
    List<Team> findTeamsByCountry(String country);
    Team findTeamByCode(String code);
    boolean deleteTeam(String code);
    Team  updateTeam(Team team);

}
