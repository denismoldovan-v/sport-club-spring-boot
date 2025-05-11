package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "typy_oplat")
public class TypyOplat {
    @Id
    @Column(name = "id_typu", nullable = false)
    private Integer id;

    @Column(name = "nazwa", nullable = false, length = 20)
    private String nazwa;

    @Column(name = "opis", nullable = false, length = 200)
    private String opis;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "oplata_id", nullable = false)
    private OplatyDodatkowe oplata;

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

    public OplatyDodatkowe getOplata() {
        return oplata;
    }

    public void setOplata(OplatyDodatkowe oplata) {
        this.oplata = oplata;
    }

}