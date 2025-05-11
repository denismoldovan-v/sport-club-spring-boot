package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Trenerzy\"")
public class Trenerzy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pracownik_id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "typ_sportu", nullable = false, length = 15)
    private String typSportu;

    @Column(name = "doswiadczenie", nullable = false, length = 400)
    private String doswiadczenie;

    @Column(name = "nr_licencji", nullable = false, length = 15)
    private String nrLicencji;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypSportu() {
        return typSportu;
    }

    public void setTypSportu(String typSportu) {
        this.typSportu = typSportu;
    }

    public String getDoswiadczenie() {
        return doswiadczenie;
    }

    public void setDoswiadczenie(String doswiadczenie) {
        this.doswiadczenie = doswiadczenie;
    }

    public String getNrLicencji() {
        return nrLicencji;
    }

    public void setNrLicencji(String nrLicencji) {
        this.nrLicencji = nrLicencji;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}