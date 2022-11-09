package com.sofka.tour.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Team")
@Table(name = "team")
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 45, nullable=false, unique=false)
    @Size(min = 3, max = 45, message="Name criteria is wrong")
    private String name;

    @Column(name = "team_code", length = 3, nullable=false, unique=true, updatable = false)
    @Size(min = 3, max = 3, message="Team code must be 3 characters")
    private String teamCode;

    @Column(name = "country", length = 45, nullable=false, unique=false)
    @Size(min = 3, max = 45, message="Country criteria is wrong")
    private String country;

    @OneToMany(
            mappedBy = "fkTeamId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Cyclist> cyclists= new ArrayList<>(8);

    public Team addCyclist(Cyclist cyclist){
        this.cyclists.add(cyclist);
        return this;
    }

    public Team removeCyclist(Cyclist cyclist){
        this.cyclists.remove(cyclist);
        return this;
    }
}
