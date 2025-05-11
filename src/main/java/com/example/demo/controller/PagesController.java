package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    private final UserRepository userRepository;

    public PagesController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/", "/home"}) // Mapping for the homepage
    public String home() {
        return "home"; // Refers to home.html in the templates folder
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Refers to login.html in the templates folder
    }

    @GetMapping("/kluby")
    public String klubyPage() {
        return "kluby"; // Refers to kluby.html in the templates folder
    }

    @GetMapping("/wydarzenia")
    public String wydarzenia() {
        return "wydarzenia"; // Refers to wydarzenia.html in the templates folder
    }

    @GetMapping("/zawodnicy")
    public String zawodnicyPage() {
        return "zawodnicy"; // Refers to zawodnicy.html in the templates folder
    }

    // Additional methods can go here
}
