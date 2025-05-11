package com.example.demo.repository;

import com.example.demo.model.Zarzadzcy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZarzadzcyRepository extends JpaRepository<Zarzadzcy, Integer> {
}