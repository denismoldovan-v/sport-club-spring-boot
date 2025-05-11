package com.example.demo.repository;

import com.example.demo.model.Wyplaty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WyplatyRepository extends JpaRepository<Wyplaty, Integer> {
}