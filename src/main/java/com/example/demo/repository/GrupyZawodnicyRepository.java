package com.example.demo.repository;

import com.example.demo.model.Grupy;
import com.example.demo.model.GrupyZawodnicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupyZawodnicyRepository extends JpaRepository<GrupyZawodnicy, Long> {
    List<GrupyZawodnicy> findByGrupaId(Long groupId);
}
