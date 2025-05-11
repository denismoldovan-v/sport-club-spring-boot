package com.example.demo.repository;

import com.example.demo.model.KlubZawodnik;
import com.example.demo.model.KlubZawodnikId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlubZawodnikRepository extends JpaRepository<KlubZawodnik, KlubZawodnikId> {
}