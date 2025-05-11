package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.TerminyTreningow;
import com.example.demo.repository.TerminyTreningowRepository;

import java.util.List;

@RestController
@RequestMapping("/api/terminyTreningow")
public class TerminyTreningowController {

    @Autowired
    private TerminyTreningowRepository terminyTreningowRepository;

    // GET all training session times
    @GetMapping
    public List<TerminyTreningow> getAllTrainingSessions() {
        return terminyTreningowRepository.findAll();
    }

    // GET one training session time by ID
    @GetMapping("/{id}")
    public ResponseEntity<TerminyTreningow> getTrainingSessionById(@PathVariable Integer id) {
        return terminyTreningowRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new training session time
    @PostMapping
    public TerminyTreningow createTrainingSession(@RequestBody TerminyTreningow newSession) {
        return terminyTreningowRepository.save(newSession);
    }

    // PUT to update an existing training session time
    @PutMapping("/{id}")
    public ResponseEntity<TerminyTreningow> updateTrainingSession(@PathVariable Integer id, @RequestBody TerminyTreningow updatedSession) {
        return terminyTreningowRepository.findById(id)
                .map(existingSession -> {
                    existingSession.setCzasRozpoczecia(updatedSession.getCzasRozpoczecia());
                    existingSession.setCzasZakonczenia(updatedSession.getCzasZakonczenia());
                    return ResponseEntity.ok(terminyTreningowRepository.save(existingSession));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a training session time
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainingSession(@PathVariable Integer id) {
        return terminyTreningowRepository.findById(id)
                .map(session -> {
                    terminyTreningowRepository.delete(session);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
