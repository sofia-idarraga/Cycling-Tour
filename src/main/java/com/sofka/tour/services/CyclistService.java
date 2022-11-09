package com.sofka.tour.services;

import com.sofka.tour.domain.Cyclist;
import com.sofka.tour.domain.Team;
import com.sofka.tour.repository.CyclistRepository;
import com.sofka.tour.repository.TeamRepository;
import com.sofka.tour.services.interfaces.ICyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CyclistService implements ICyclistService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CyclistRepository cyclistRepository;

    @Override
    public String addCyclist(Cyclist cyclist) {
        Optional<Team> team = teamRepository.findById(cyclist.getFkTeamId());
        if(team.isEmpty()){
            return "Team doesn't exist";
        }
        if (team.get().getCyclists().size()<8){
            cyclistRepository.save(cyclist);
            return "Cyclist added: "+cyclist.toString();
        }
        else return "Team already has 8 cyclists";
    }

    @Override
    public Cyclist findCyclistByCode(String code) {
        return cyclistRepository.findCyclistByCyclistCode(code);
    }

    @Override
    public List<Cyclist> findCyclistsByCountry(String country) {
        return cyclistRepository.findCyclistsByCountry(country);
    }

    @Override
    public Boolean deleteCyclist(String code) {
        Cyclist cyclist = cyclistRepository.findCyclistByCyclistCode(code);
        if (cyclist == null){
            return false;
        }
        Team team = teamRepository.findById(cyclist.getFkTeamId()).get();
        team.removeCyclist(cyclist);
        teamRepository.save(team);
        cyclistRepository.deleteById(cyclist.getId());
        return true;
    }

    @Override
    public Cyclist updateCyclist(Cyclist cyclist) {
        Cyclist cyclistToUpdate = cyclistRepository.findCyclistByCyclistCode(cyclist.getCyclistCode());
        if (cyclistToUpdate != null){
            cyclist.setId(cyclistToUpdate.getId());
            cyclistRepository.save(cyclist);
            return cyclist;
        }
        return null;
    }
}
