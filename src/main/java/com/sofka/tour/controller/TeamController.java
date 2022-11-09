package com.sofka.tour.controller;

import com.sofka.tour.domain.Team;
import com.sofka.tour.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class TeamController {

    @Autowired
    private TeamService teamService;
    private HttpStatus httpStatus = HttpStatus.OK;
    private Object data = null;

    @GetMapping("/teams")
    public List<Team> findAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/team/code/{code}")
    public ResponseEntity<Object> findTeamByCode(@PathVariable(name = "code") String code) {
        try {
            data = teamService.findTeamByCode(code);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            data = e.getCause().getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(data, httpStatus);
    }

    @GetMapping("/team/country/{country}")
    public ResponseEntity<Object> findTeamByCountry(@PathVariable(name = "country") String country) {
        try {
            data = teamService.findTeamsByCountry(country);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            data = e.getCause().getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(data, httpStatus);
    }

    @PostMapping("/new/team")
    public ResponseEntity<Object> registerTeam(@RequestBody Team team) {
        try {
            data = teamService.registerTeam(team);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            data = e.getCause().getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(data, httpStatus);
    }

    @DeleteMapping("delete/team/{code}")
    public ResponseEntity<Object> deleteTeam(@PathVariable(name = "code") String code) {
        Boolean state = teamService.deleteTeam(code);
        if (state) {
            data = "Team deleted: " + code;
            httpStatus = HttpStatus.OK;
        } else {
            data = "Cannot be deleted, check the code is ok";
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(data, httpStatus);
    }

    @PutMapping("update/team")
    public ResponseEntity<Object> updateTeam(@RequestBody Team team) {
        try {
            data = teamService.updateTeam(team);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            data = e.getCause().getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(data, httpStatus);
    }
}
