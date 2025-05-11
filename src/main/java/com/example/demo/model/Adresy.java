package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Adresy\"")
public class Adresy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adres_id", nullable = false)
    private Integer id;

    @Column(name = "miasto", nullable = false, length = 20)
    private String miasto;

    @Column(name = "kraj", nullable = false, length = 25)
    private String kraj;

    @Column(name = "kod_pocztowy", nullable = false, length = 6)
    private String kodPocztowy;

    @Column(name = "nazwa_ulicy", nullable = false, length = 20)
    private String nazwaUlicy;

    @Column(name = "nr_domu", nullable = false, length = 5)
    private String nrDomu;

    @Column(name = "nr_lokalu", length = 5)
    private String nrLokalu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getNazwaUlicy() {
        return nazwaUlicy;
    }

    public void setNazwaUlicy(String nazwaUlicy) {
        this.nazwaUlicy = nazwaUlicy;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    public String getNrLokalu() {
        return nrLokalu;
    }

    public void setNrLokalu(String nrLokalu) {
        this.nrLokalu = nrLokalu;
    }

}