package com.sofka.tour.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity(name = "Cyclist")
@Table(name = "cyclist")
@Data
public class Cyclist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    @Size(min = 3, max = 45, message="Name criteria not met")
    private String name;

    @Column(name = "cyclist_code", nullable = false, unique = true, updatable = false, length = 3)
    @Size(min = 3, max = 3, message="Cyclist code must be 3 characters")
    private String cyclistCode;

    @Column(name = "country", length = 45, nullable=false, unique=false)
    @Size(min = 3, max = 45, message="Country criteria is wrong")
    private String country;

    private Long fkTeamId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cyclist cyclist = (Cyclist) o;
        return Objects.equals(id, cyclist.id) && Objects.equals(name, cyclist.name) && Objects.equals(cyclistCode, cyclist.cyclistCode) && Objects.equals(country, cyclist.country) && Objects.equals(fkTeamId, cyclist.fkTeamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cyclistCode, country, fkTeamId);
    }
}
