package com.example.demo.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "\"Pracownicy\"")
public class Pracownicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pracownik_id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "imie", nullable = false, length = 20)
    private String imie;

    @Column(name = "nazwisko", nullable = false, length = 20)
    private String nazwisko;

    @Column(name = "telefon", nullable = false, length = 15)
    private String telefon;

    @Column(name = "\"PESEL\"", length = 11)
    private String pesel;

    @Column(name = "data_urodzenia", nullable = false)
    private Instant dataUrodzenia;

    @Column(name = "data_zatrudnienia", nullable = false)
    private Instant dataZatrudnienia;

    @Column(name = "pensja", nullable = false)
    private Double pensja;

    @Column(name = "data_zwolnienia")
    private Instant dataZwolnienia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klub_id", nullable = false)
    private KlubyLekkoatletyczne klub;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adres_id", nullable = false)
    private Adresy adres;

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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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

    public Instant getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(Instant dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public Double getPensja() {
        return pensja;
    }

    public void setPensja(Double pensja) {
        this.pensja = pensja;
    }

    public Instant getDataZwolnienia() {
        return dataZwolnienia;
    }

    public void setDataZwolnienia(Instant dataZwolnienia) {
        this.dataZwolnienia = dataZwolnienia;
    }

    public KlubyLekkoatletyczne getKlub() {
        return klub;
    }

    public void setKlub(KlubyLekkoatletyczne klub) {
        this.klub = klub;
    }

    public Adresy getAdres() {
        return adres;
    }

    public void setAdres(Adresy adres) {
        this.adres = adres;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}