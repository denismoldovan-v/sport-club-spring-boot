package com.example.demo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "\"Zawodnicy\"")
public class Zawodnicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zawodnik_id", nullable = false)
    private Integer id;

    @Column(name = "imie", nullable = false, length = 20)
    private String imie;

    @Column(name = "nazwisko", nullable = false, length = 20)
    private String nazwisko;

    @Column(name = "numer_telefonu", nullable = false, length = 20)
    private String numerTelefonu;

    @Column(name = "data_urodzenia", nullable = false)
    private Instant dataUrodzenia;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "oplata_subskrypcyjna", precision = 10, scale = 2)
    private BigDecimal oplataSubskrypcyjna;

    // Add One-to-One relationship with User
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;


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

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public Instant getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Instant dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getOplataSubskrypcyjna() {
        return oplataSubskrypcyjna;
    }

    public void setOplataSubskrypcyjna(BigDecimal oplataSubskrypcyjna) {
        this.oplataSubskrypcyjna = oplataSubskrypcyjna;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}