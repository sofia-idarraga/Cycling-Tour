package com.sofka.tour.services.interfaces;

import com.sofka.tour.domain.Cyclist;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICyclistService{

    String addCyclist(Cyclist cyclist);
    Cyclist findCyclistByCode(String code);
    List<Cyclist> findCyclistsByCountry(String country);
    Boolean deleteCyclist(String code);

    Cyclist updateCyclist(Cyclist cyclist);
}
