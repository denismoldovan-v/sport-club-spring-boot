package com.example.demo.controller;

import com.example.demo.model.RoleUpgradeRequest;
import com.example.demo.model.User;
import com.example.demo.repository.RoleUpgradeRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class UpgradeController {

    private final UserRepository userRepository;
    private final RoleUpgradeRequestRepository roleUpgradeRequestRepository;

    public UpgradeController(UserRepository userRepository, RoleUpgradeRequestRepository roleUpgradeRequestRepository) {
        this.userRepository = userRepository;
        this.roleUpgradeRequestRepository = roleUpgradeRequestRepository;
    }

    @PostMapping("/upgrade-role")
    public String requestRoleUpgrade(@RequestParam String newRole, Authentication authentication) {
        String username = authentication.getName();

        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        if (roleUpgradeRequestRepository.existsByUserAndStatus(user, "PENDING")) {
            return "redirect:/profile?error=PendingRequest";
        }

        RoleUpgradeRequest request = new RoleUpgradeRequest();
        request.setUser(user);
        request.setRequestedRole(newRole);
        request.setStatus("PENDING");
        request.setRequestDate(LocalDateTime.now());

        roleUpgradeRequestRepository.save(request);
        return "redirect:/profile?success=RequestSubmitted";
    }
}

