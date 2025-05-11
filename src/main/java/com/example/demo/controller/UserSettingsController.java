package com.example.demo.controller;

import com.example.demo.model.RoleUpgradeRequest;
import com.example.demo.model.User;
import com.example.demo.repository.RoleUpgradeRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class UserSettingsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleUpgradeRequestRepository roleUpgradeRequestRepository;

    @PostMapping("/role-upgrade")
    public String requestRoleUpgrade(@RequestParam String requestedRole, Principal principal) {
        // Fetch the logged-in user
        User user = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create a new role upgrade request
        RoleUpgradeRequest request = new RoleUpgradeRequest();
        request.setUser(user);
        request.setRequestedRole(requestedRole);
        request.setStatus("PENDING");
        request.setRequestDate(LocalDateTime.now());

        // Save the request to the database
        roleUpgradeRequestRepository.save(request);

        // Redirect with success message
        return "redirect:/settings?success=Request submitted";
    }
}
