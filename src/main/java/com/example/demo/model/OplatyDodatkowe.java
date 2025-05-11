package com.example.demo.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "\"Oplaty_dodatkowe\"")
public class OplatyDodatkowe {
    @Id
    @Column(name = "oplata_id", nullable = false)
    private Integer id;

    @Column(name = "rozmiar", nullable = false)
    private Integer rozmiar;

    @Column(name = "data", nullable = false)
    private Instant data;

    @Column(name = "typ_oplaty", nullable = false, length = 12)
    private String typOplaty;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klub_id", nullable = false)
    private KlubyLekkoatletyczne klub;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(Integer rozmiar) {
        this.rozmiar = rozmiar;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public String getTypOplaty() {
        return typOplaty;
    }

    public void setTypOplaty(String typOplaty) {
        this.typOplaty = typOplaty;
    }

    public KlubyLekkoatletyczne getKlub() {
        return klub;
    }

    public void setKlub(KlubyLekkoatletyczne klub) {
        this.klub = klub;
    }

}