package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Grupy_Terminy_Treningow\"")
public class GrupyTerminyTreningow {
    @EmbeddedId
    private GrupyTerminyTreningowId id;

    @MapsId("grupaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grupa_id", nullable = false)
    private Grupy grupa;

    @MapsId("terminId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "termin_id", nullable = false)
    private TerminyTreningow termin;

    public GrupyTerminyTreningowId getId() {
        return id;
    }

    public void setId(GrupyTerminyTreningowId id) {
        this.id = id;
    }

    public Grupy getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupy grupa) {
        this.grupa = grupa;
    }

    public TerminyTreningow getTermin() {
        return termin;
    }

    public void setTermin(TerminyTreningow termin) {
        this.termin = termin;
    }

}