package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Trenerzy;
import com.example.demo.repository.TrenerzyRepository;

import java.util.List;

@RestController
@RequestMapping("/api/trenerzy")
public class TrenerzyController {

    @Autowired
    private TrenerzyRepository trenerzyRepository;

    // GET all coaches
    @GetMapping
    public List<Trenerzy> getAllCoaches() {
        return trenerzyRepository.findAll();
    }

    // GET one coach by ID
    @GetMapping("/{id}")
    public ResponseEntity<Trenerzy> getCoachById(@PathVariable Integer id) {
        return trenerzyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new coach
    @PostMapping
    public Trenerzy createCoach(@RequestBody Trenerzy newCoach) {
        return trenerzyRepository.save(newCoach);
    }

    // PUT to update an existing coach
    @PutMapping("/{id}")
    public ResponseEntity<Trenerzy> updateCoach(@PathVariable Integer id, @RequestBody Trenerzy updatedCoach) {
        return trenerzyRepository.findById(id)
                .map(existingCoach -> {
                    existingCoach.setTypSportu(updatedCoach.getTypSportu());
                    existingCoach.setDoswiadczenie(updatedCoach.getDoswiadczenie());
                    existingCoach.setNrLicencji(updatedCoach.getNrLicencji());
                    return ResponseEntity.ok(trenerzyRepository.save(existingCoach));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a coach
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoach(@PathVariable Integer id) {
        return trenerzyRepository.findById(id)
                .map(coach -> {
                    trenerzyRepository.delete(coach);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
