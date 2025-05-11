package com.example.demo.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "\"Zarzadzcy\"")
public class Zarzadzcy {
    @Id
    @Column(name = "zarzadzca_id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "imie", nullable = false, length = 30)
    private String imie;

    @Column(name = "nazwisko", nullable = false, length = 30)
    private String nazwisko;

    @Column(name = "\"PESEL\"", length = 11)
    private String pesel;

    @Column(name = "data_urodzenia", nullable = false)
    private Instant dataUrodzenia;

    @Column(name = "data_przypisania", nullable = false)
    private Instant dataPrzypisania;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Instant getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Instant dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public Instant getDataPrzypisania() {
        return dataPrzypisania;
    }

    public void setDataPrzypisania(Instant dataPrzypisania) {
        this.dataPrzypisania = dataPrzypisania;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}