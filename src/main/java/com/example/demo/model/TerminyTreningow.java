package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "\"Terminy_treningow\"")
public class TerminyTreningow {
    @Id
    @Column(name = "termin_id", nullable = false)
    private Integer id;

    @Column(name = "czas_rozpoczecia", nullable = false)
    private Instant czasRozpoczecia;

    @Column(name = "czas_zakonczenia", nullable = false)
    private Instant czasZakonczenia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getCzasRozpoczecia() {
        return czasRozpoczecia;
    }

    public void setCzasRozpoczecia(Instant czasRozpoczecia) {
        this.czasRozpoczecia = czasRozpoczecia;
    }

    public Instant getCzasZakonczenia() {
        return czasZakonczenia;
    }

    public void setCzasZakonczenia(Instant czasZakonczenia) {
        this.czasZakonczenia = czasZakonczenia;
    }

}