package com.sofka.tour.repository;

import com.sofka.tour.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Size;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team , Long> {

    Team findTeamByTeamCode(@Size(min = 3, max = 3, message = "Team code must be 3 characters") String teamCode);
    List<Team> findTeamsByCountry(@Size(min = 3, max = 45, message = "Country criteria is wrong") String country);
}
