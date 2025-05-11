package com.example.demo.repository;

import com.example.demo.model.Trenerzy;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrenerzyRepository extends JpaRepository<Trenerzy, Integer> {
    Optional<Object> findByUser(User user);
}