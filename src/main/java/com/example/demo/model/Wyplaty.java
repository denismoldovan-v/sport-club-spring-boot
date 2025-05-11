package com.example.demo.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "\"Wyplaty\"")
public class Wyplaty {
    @Id
    @Column(name = "wyplata_id", nullable = false)
    private Integer id;

    @Column(name = "data", nullable = false)
    private Instant data;

    @Column(name = "tytul_wyplaty", nullable = false, length = 30)
    private String tytulWyplaty;

    @Column(name = "metoda_platnosci", nullable = false, length = 20)
    private String metodaPlatnosci;

    @Column(name = "nr_konta", length = 30)
    private String nrKonta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pracownik_id", nullable = false)
    private Pracownicy pracownik;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public String getTytulWyplaty() {
        return tytulWyplaty;
    }

    public void setTytulWyplaty(String tytulWyplaty) {
        this.tytulWyplaty = tytulWyplaty;
    }

    public String getMetodaPlatnosci() {
        return metodaPlatnosci;
    }

    public void setMetodaPlatnosci(String metodaPlatnosci) {
        this.metodaPlatnosci = metodaPlatnosci;
    }

    public String getNrKonta() {
        return nrKonta;
    }

    public void setNrKonta(String nrKonta) {
        this.nrKonta = nrKonta;
    }

    public Pracownicy getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownicy pracownik) {
        this.pracownik = pracownik;
    }

}