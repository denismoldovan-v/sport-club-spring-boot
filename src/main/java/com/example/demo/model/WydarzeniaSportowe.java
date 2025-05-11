package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "\"Wydarzenia_sportowe\"")
public class WydarzeniaSportowe {
    @Id
    @Column(name = "wydarzenie_id", nullable = false)
    private Integer id;

    @Column(name = "data", nullable = false)
    private Instant data;

    @Column(name = "nazwa", nullable = false, length = 20)
    private String nazwa;

    @Column(name = "typ_sportu", nullable = false, length = 15)
    private String typSportu;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "klub_id", nullable = false)
    @JsonManagedReference
    private KlubyLekkoatletyczne klub;

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

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTypSportu() {
        return typSportu;
    }

    public void setTypSportu(String typSportu) {
        this.typSportu = typSportu;
    }

    public KlubyLekkoatletyczne getKlub() {
        return klub;
    }

    public void setKlub(KlubyLekkoatletyczne klub) {
        this.klub = klub;
    }

}