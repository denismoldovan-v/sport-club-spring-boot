package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "\"Zawodnicy_Wydarzenia_Sportowe\"")
public class ZawodnicyWydarzeniaSportowe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zawodnik_id", nullable = false)
    private Integer zawodnikId;

    @Column(name = "wydarzenie_id", nullable = false)
    private Integer wydarzenieId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "osiagniecie_id", nullable = false)
    private OsiagnieciaSportowe osiagniecie;

    public Integer getZawodnikId() {
        return zawodnikId;
    }

    public void setZawodnikId(Integer zawodnikId) {
        this.zawodnikId = zawodnikId;
    }

    public Integer getWydarzenieId() {
        return wydarzenieId;
    }

    public void setWydarzenieId(Integer wydarzenieId) {
        this.wydarzenieId = wydarzenieId;
    }

    public OsiagnieciaSportowe getOsiagniecie() {
        return osiagniecie;
    }

    public void setOsiagniecie(OsiagnieciaSportowe osiagniecie) {
        this.osiagniecie = osiagniecie;
    }

}