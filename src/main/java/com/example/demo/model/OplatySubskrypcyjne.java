package com.example.demo.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "\"Opłaty_subskrypcyjne\"")
public class OplatySubskrypcyjne {
    @Id
    @Column(name = "oplata_id", nullable = false)
    private Integer id;

    @Column(name = "poczatek_subskrypcji", nullable = false)
    private Instant poczatekSubskrypcji;

    @Column(name = "koniec_subskrypcji", nullable = false)
    private Instant koniecSubskrypcji;

    @Column(name = "rozmiar_oplaty", nullable = false)
    private Integer rozmiarOplaty;

    @Column(name = "typ_subskrypcji", nullable = false, length = 30)
    private String typSubskrypcji;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zawodnik_id", nullable = false)
    private Zawodnicy zawodnik;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getPoczatekSubskrypcji() {
        return poczatekSubskrypcji;
    }

    public void setPoczatekSubskrypcji(Instant poczatekSubskrypcji) {
        this.poczatekSubskrypcji = poczatekSubskrypcji;
    }

    public Instant getKoniecSubskrypcji() {
        return koniecSubskrypcji;
    }

    public void setKoniecSubskrypcji(Instant koniecSubskrypcji) {
        this.koniecSubskrypcji = koniecSubskrypcji;
    }

    public Integer getRozmiarOplaty() {
        return rozmiarOplaty;
    }

    public void setRozmiarOplaty(Integer rozmiarOplaty) {
        this.rozmiarOplaty = rozmiarOplaty;
    }

    public String getTypSubskrypcji() {
        return typSubskrypcji;
    }

    public void setTypSubskrypcji(String typSubskrypcji) {
        this.typSubskrypcji = typSubskrypcji;
    }

    public Zawodnicy getZawodnik() {
        return zawodnik;
    }

    public void setZawodnik(Zawodnicy zawodnik) {
        this.zawodnik = zawodnik;
    }

}