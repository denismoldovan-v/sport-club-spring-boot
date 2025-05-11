package com.example.demo.dto;

public class UserDTO {
    private Integer userId; // ID пользователя
    private String login;   // Логин пользователя
    private String role;    // Роль пользователя
    private String passwordHash;

    // Геттеры и сеттеры
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}

