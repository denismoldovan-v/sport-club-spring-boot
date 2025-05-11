package com.example.demo.dto;

import java.time.Instant;

public class ParticipantResponse {
    private String name;
    private String role;
    private Instant dataPrzypisania;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getDataPrzypisania() {
        return dataPrzypisania;
    }

    public void setDataPrzypisania(Instant dataPrzypisania) {
        this.dataPrzypisania = dataPrzypisania;
    }
}
