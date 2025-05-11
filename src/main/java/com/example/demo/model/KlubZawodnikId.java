package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KlubZawodnikId implements Serializable {
    private static final long serialVersionUID = 5623475911150749158L;

    @Column(name = "zawodnik_id", nullable = false)
    private Integer zawodnikId;

    @Column(name = "klub_id", nullable = false)
    private Integer klubId;

    // Default constructor needed by JPA
    public KlubZawodnikId() {
    }

    // Constructor to initialize both IDs
    public KlubZawodnikId(Integer zawodnikId, Integer klubId) {
        this.zawodnikId = zawodnikId;
        this.klubId = klubId;
    }

    public Integer getZawodnikId() {
        return zawodnikId;
    }

    public void setZawodnikId(Integer zawodnikId) {
        this.zawodnikId = zawodnikId;
    }

    public Integer getKlubId() {
        return klubId;
    }

    public void setKlubId(Integer klubId) {
        this.klubId = klubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        KlubZawodnikId entity = (KlubZawodnikId) o;
        return Objects.equals(this.klubId, entity.klubId) &&
                Objects.equals(this.zawodnikId, entity.zawodnikId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klubId, zawodnikId);
    }
}
