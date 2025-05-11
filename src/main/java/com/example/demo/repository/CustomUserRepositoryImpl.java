package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByLogin(String login) {
        System.out.println("findByLogin called for login: " + login);
        return userRepository.findByLogin(login);
    }
}
