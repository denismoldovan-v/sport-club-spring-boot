package com.example.demo.repository;

import com.example.demo.model.Pracownicy;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PracownicyRepository extends JpaRepository<Pracownicy, Integer> {
    Collection<Object> findByUser(User user);
}