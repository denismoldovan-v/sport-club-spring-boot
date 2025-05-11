package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        if (userRepository.findByLogin(username).isPresent()) {
            model.addAttribute("error", "User already exists!");
            return "register";
        }

        User newUser = new User();
        newUser.setLogin(username);
        newUser.setPasswordHash(passwordEncoder.encode(password));
        newUser.setRole("USER"); // Save without "ROLE_" prefix

        userRepository.save(newUser);

        model.addAttribute("success", "User registered successfully!");
        return "register";
    }
}
