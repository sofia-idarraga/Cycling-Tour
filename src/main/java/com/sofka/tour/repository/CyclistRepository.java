package com.sofka.tour.repository;

import com.sofka.tour.domain.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Size;
import java.util.List;

public interface CyclistRepository extends JpaRepository<Cyclist, Long> {

    Cyclist findCyclistByCyclistCode(@Size(min = 3, max = 3, message = "Cyclist code must be 3 characters") String cyclistCode);
    List<Cyclist> findCyclistsByCountry(@Size(min = 3, max = 45, message = "Country criteria is wrong") String country);
}
