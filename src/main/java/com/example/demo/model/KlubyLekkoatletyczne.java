package com.example.demo.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "\"Kluby_lekkoatletyczne\"")
public class KlubyLekkoatletyczne {
    @Id
    @Column(name = "klub_id", nullable = false)
    private Integer id;

    @Column(name = "nazwa", nullable = false, length = 30)
    private String nazwa;

    @Column(name = "data_zalozenia", nullable = false)
    private Instant dataZalozenia;

    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zarzadzca_id", nullable = false)
    private Zarzadzcy zarzadzca;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adres_id", nullable = false)
    private Adresy adres;

    @OneToMany(mappedBy = "klub", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WydarzeniaSportowe> wydarzeniaSportowe;

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

    public Instant getDataZalozenia() {
        return dataZalozenia;
    }

    public void setDataZalozenia(Instant dataZalozenia) {
        this.dataZalozenia = dataZalozenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Zarzadzcy getZarzadzca() {
        return zarzadzca;
    }

    public void setZarzadzca(Zarzadzcy zarzadzca) {
        this.zarzadzca = zarzadzca;
    }

    public Adresy getAdres() {
        return adres;
    }

    public void setAdres(Adresy adres) {
        this.adres = adres;
    }

}