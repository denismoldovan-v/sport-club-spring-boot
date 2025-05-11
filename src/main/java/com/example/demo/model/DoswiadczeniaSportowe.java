package com.example.demo.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "\"Doswiadczenia_sportowe\"")
public class DoswiadczeniaSportowe {
    @Id
    @Column(name = "doswiadczenie_id", nullable = false)
    private Integer id;

    @Column(name = "nazwa", nullable = false, length = 20)
    private String nazwa;

    @Column(name = "opis", nullable = false, length = 400)
    private String opis;

    @Column(name = "data_rozpoczecia", nullable = false)
    private Instant dataRozpoczecia;

    @Column(name = "data_zakonczenia")
    private Instant dataZakonczenia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pracownik_id", nullable = false)
    private Trenerzy pracownik;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Instant getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Instant dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public Instant getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Instant dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public Trenerzy getPracownik() {
        return pracownik;
    }

    public void setPracownik(Trenerzy pracownik) {
        this.pracownik = pracownik;
    }

}