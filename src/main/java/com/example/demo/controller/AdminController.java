package com.example.demo.controller;

import com.example.demo.model.RoleUpgradeRequest;
import com.example.demo.model.User;
import com.example.demo.repository.RoleUpgradeRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleUpgradeRequestRepository roleUpgradeRequestRepository;
    private final UserRepository userRepository;

    public AdminController(RoleUpgradeRequestRepository roleUpgradeRequestRepository, UserRepository userRepository) {
        this.roleUpgradeRequestRepository = roleUpgradeRequestRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/role-requests")
    public String viewRoleRequests(
            @RequestParam(defaultValue = "0") int page, // Current page number
            @RequestParam(defaultValue = "5") int size, // Number of items per page
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RoleUpgradeRequest> requestsPage = roleUpgradeRequestRepository.findByStatus("PENDING", pageable);

        model.addAttribute("requests", requestsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", requestsPage.getTotalPages());
        model.addAttribute("totalElements", requestsPage.getTotalElements());
        model.addAttribute("pageSize", size);
        return "admin/role_requests"; // Refers to the admin/role_requests.html template
    }

    @PostMapping("/role-requests/{id}/approve")
    public String approveRequest(@PathVariable Integer id, Principal principal) {
        RoleUpgradeRequest request = roleUpgradeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        // Update the request status and admin information
        request.setStatus("APPROVED");
        request.setReviewDate(LocalDateTime.now());
        User admin = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        request.setReviewedBy(admin);

        // Update the user's role
        User user = request.getUser();
        user.setRole(request.getRequestedRole());
        userRepository.save(user);
        roleUpgradeRequestRepository.save(request);

        return "redirect:/admin/role-requests?success=Approved";
    }

    @PostMapping("/role-requests/{id}/decline")
    public String declineRequest(@PathVariable Integer id, Principal principal) {
        RoleUpgradeRequest request = roleUpgradeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        // Update the request status and admin information
        request.setStatus("DECLINED");
        request.setReviewDate(LocalDateTime.now());
        User admin = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        request.setReviewedBy(admin);

        roleUpgradeRequestRepository.save(request);
        return "redirect:/admin/role-requests?success=Declined";
    }
}
