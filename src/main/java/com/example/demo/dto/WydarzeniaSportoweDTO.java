package com.example.demo.dto;

import java.time.Instant;

public class WydarzeniaSportoweDTO {
    private Integer id;
    private Instant data;
    private String nazwa;
    private String typSportu;
    private KlubyLekkoatletyczneDTO klub; // Include the club DTO instead of the entity

    // Getters and setters
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

    public KlubyLekkoatletyczneDTO getKlub() {
        return klub;
    }

    public void setKlub(KlubyLekkoatletyczneDTO klub) {
        this.klub = klub;
    }
}
