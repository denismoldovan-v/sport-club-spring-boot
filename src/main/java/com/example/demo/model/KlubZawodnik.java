package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Klub_Zawodnik\"")
public class KlubZawodnik {
    @EmbeddedId
    private KlubZawodnikId id;

    @MapsId("zawodnikId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zawodnik_id", nullable = false)
    private Zawodnicy zawodnik;

    @MapsId("klubId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klub_id", nullable = false)
    private KlubyLekkoatletyczne klub;

    public KlubZawodnikId getId() {
        return id;
    }

    public void setId(KlubZawodnikId id) {
        this.id = id;
    }

    public Zawodnicy getZawodnik() {
        return zawodnik;
    }

    public void setZawodnik(Zawodnicy zawodnik) {
        this.zawodnik = zawodnik;
    }

    public KlubyLekkoatletyczne getKlub() {
        return klub;
    }

    public void setKlub(KlubyLekkoatletyczne klub) {
        this.klub = klub;
    }

}