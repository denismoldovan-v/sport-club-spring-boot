package com.example.demo.repository;

import com.example.demo.model.KlubyLekkoatletyczne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KlubyLekkoatletyczneRepository extends JpaRepository<KlubyLekkoatletyczne, Integer> {
    Optional<KlubyLekkoatletyczne> findByNazwa(String nazwa); // Custom query method
}