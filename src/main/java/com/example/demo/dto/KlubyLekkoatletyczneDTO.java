package com.example.demo.dto;

import java.time.Instant;

public class KlubyLekkoatletyczneDTO {
    private Integer id;
    private String nazwa;
    private Instant dataZalozenia;
    private String email;

    // Getters and setters
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
}
