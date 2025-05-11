package com.example.demo.repository;

import com.example.demo.model.WydarzeniaSportowe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WydarzeniaSportoweRepository extends JpaRepository<WydarzeniaSportowe, Integer> {
    Page<WydarzeniaSportowe> findAll(Pageable pageable);
}