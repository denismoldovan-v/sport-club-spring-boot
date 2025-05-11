package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "\"Grupy_Zawodnicy\"")
public class GrupyZawodnicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grupa_id", referencedColumnName = "grupa_id", nullable = false)
    private Grupy grupa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zawodnik_id", referencedColumnName = "zawodnik_id", nullable = false)
    private Zawodnicy zawodnik;

    @Column(name = "data_przypisania", nullable = false)
    private Instant dataPrzypisania;

    @Column(name = "data_wypisania")
    private Instant dataWypisania;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Grupy getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupy grupa) {
        this.grupa = grupa;
    }

    public Zawodnicy getZawodnik() {
        return zawodnik;
    }

    public void setZawodnik(Zawodnicy zawodnik) {
        this.zawodnik = zawodnik;
    }

    public Instant getDataPrzypisania() {
        return dataPrzypisania;
    }

    public void setDataPrzypisania(Instant dataPrzypisania) {
        this.dataPrzypisania = dataPrzypisania;
    }

    public Instant getDataWypisania() {
        return dataWypisania;
    }

    public void setDataWypisania(Instant dataWypisania) {
        this.dataWypisania = dataWypisania;
    }
}
