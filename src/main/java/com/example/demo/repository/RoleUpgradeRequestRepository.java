package com.example.demo.repository;

import com.example.demo.model.RoleUpgradeRequest;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleUpgradeRequestRepository extends JpaRepository<RoleUpgradeRequest, Integer> {

    // Check if a user has a specific pending role upgrade request
    boolean existsByUserAndStatus(User user, String status);

    // Retrieve all role upgrade requests with a specific status (paginated)
    Page<RoleUpgradeRequest> findByStatus(String status, Pageable pageable);

    // Retrieve all role upgrade requests with a specific status (non-paginated)
    List<RoleUpgradeRequest> findByStatus(String status);
}
