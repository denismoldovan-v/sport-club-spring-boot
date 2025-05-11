package com.example.demo.repository;

import com.example.demo.model.User;

import java.util.Optional;

public interface CustomUserRepository {
    Optional<User> findByLogin(String login);
}
