package com.sofka.tour.controller;

import com.sofka.tour.domain.Cyclist;
import com.sofka.tour.services.CyclistService;
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

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class CyclistController {

    @Autowired
    CyclistService cyclistService;

    private HttpStatus httpStatus = HttpStatus.OK;
    private Object data = null;

    @GetMapping("/cyclist/code/{code}")
    public ResponseEntity<Object> findCyclistByCode(@PathVariable(name = "code") String code) {
        try {
            data = cyclistService.findCyclistByCode(code);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            data = e.getCause().getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(data, httpStatus);
    }

    @GetMapping("/cyclist/country/{country}")
    public ResponseEntity<Object> findCyclistsByCountry(@PathVariable(name = "country") String country) {
        try {
            data = cyclistService.findCyclistsByCountry(country);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            data = e.getCause().getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(data, httpStatus);
    }

    @PostMapping("/new/cyclist")
    public ResponseEntity<Object> registerCyclist(@RequestBody Cyclist cyclist) {
        try {
            data = cyclistService.addCyclist(cyclist);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            data = e.getCause().getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(data, httpStatus);
    }

    @DeleteMapping("delete/cyclist/{code}")
    public ResponseEntity<Object> deleteCyclist(@PathVariable(name = "code") String code) {

        Boolean state = cyclistService.deleteCyclist(code);
        if (state) {
            data = "Cyclist deleted: " + code;
            httpStatus = HttpStatus.OK;
        } else {
            data = "Cannot be deleted, check the code is ok";
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(data, httpStatus);
    }

    @PutMapping("update/cyclist")
    public ResponseEntity<Object> updateCyclist(@RequestBody Cyclist cyclist) {
        try {
            data = cyclistService.updateCyclist(cyclist);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            data = e.getCause().getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(data, httpStatus);
    }
}
