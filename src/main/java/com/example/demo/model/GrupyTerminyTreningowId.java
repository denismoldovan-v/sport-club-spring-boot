package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GrupyTerminyTreningowId implements Serializable {
    private static final long serialVersionUID = 4972811442480853080L;
    @Column(name = "grupa_id", nullable = false)
    private Integer grupaId;

    @Column(name = "termin_id", nullable = false)
    private Integer terminId;

    public GrupyTerminyTreningowId(Integer grupaId, Integer terminId) {
    }

    public Integer getGrupaId() {
        return grupaId;
    }

    public void setGrupaId(Integer grupaId) {
        this.grupaId = grupaId;
    }

    public Integer getTerminId() {
        return terminId;
    }

    public void setTerminId(Integer terminId) {
        this.terminId = terminId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GrupyTerminyTreningowId entity = (GrupyTerminyTreningowId) o;
        return Objects.equals(this.grupaId, entity.grupaId) &&
                Objects.equals(this.terminId, entity.terminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupaId, terminId);
    }

}