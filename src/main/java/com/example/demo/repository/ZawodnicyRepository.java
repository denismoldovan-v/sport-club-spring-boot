package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.Zawodnicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ZawodnicyRepository extends JpaRepository<Zawodnicy, Integer> {
    List<Zawodnicy> getZawodnicyByUser(User user);
}