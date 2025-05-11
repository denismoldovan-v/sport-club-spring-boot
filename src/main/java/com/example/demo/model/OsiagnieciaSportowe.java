package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "\"Osiagniecia_sportowe\"")
public class OsiagnieciaSportowe {
    @Id
    @Column(name = "osiagniecie_id", nullable = false)
    private Integer id;

    @ColumnDefault("'F'")
    @Column(name = "czy_osiagnal_miejsce", nullable = false, length = Integer.MAX_VALUE)
    private String czyOsiagnalMiejsce;

    @Column(name = "miejsce_osiagniete", length = 5)
    private String miejsceOsiagniete;

    @Column(name = "opis", length = 100)
    private String opis;

    @Column(name = "\"Attribute1\"", length = 20)
    private String attribute1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCzyOsiagnalMiejsce() {
        return czyOsiagnalMiejsce;
    }

    public void setCzyOsiagnalMiejsce(String czyOsiagnalMiejsce) {
        this.czyOsiagnalMiejsce = czyOsiagnalMiejsce;
    }

    public String getMiejsceOsiagniete() {
        return miejsceOsiagniete;
    }

    public void setMiejsceOsiagniete(String miejsceOsiagniete) {
        this.miejsceOsiagniete = miejsceOsiagniete;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

}