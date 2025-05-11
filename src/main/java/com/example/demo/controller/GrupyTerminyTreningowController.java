package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.GrupyTerminyTreningow;
import com.example.demo.model.GrupyTerminyTreningowId;
import com.example.demo.repository.GrupyTerminyTreningowRepository;

import java.util.List;

@RestController
@RequestMapping("/api/grupyTerminyTreningow")
public class GrupyTerminyTreningowController {

    @Autowired
    private GrupyTerminyTreningowRepository grupyTerminyTreningowRepository;

    // GET all group training sessions
    @GetMapping
    public List<GrupyTerminyTreningow> getAllGroupTrainingSessions() {
        return grupyTerminyTreningowRepository.findAll();
    }

    // GET a single group training session by composite ID
    @GetMapping("/{grupaId}/{terminId}")
    public ResponseEntity<GrupyTerminyTreningow> getGroupTrainingSessionById(@PathVariable Integer grupaId, @PathVariable Integer terminId) {
        GrupyTerminyTreningowId id = new GrupyTerminyTreningowId(grupaId, terminId);
        return grupyTerminyTreningowRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST a new group training session
    @PostMapping
    public GrupyTerminyTreningow createGroupTrainingSession(@RequestBody GrupyTerminyTreningow newSession) {
        return grupyTerminyTreningowRepository.save(newSession);
    }

    // PUT to update an existing group training session
    @PutMapping("/{grupaId}/{terminId}")
    public ResponseEntity<GrupyTerminyTreningow> updateGroupTrainingSession(@PathVariable Integer grupaId, @PathVariable Integer terminId, @RequestBody GrupyTerminyTreningow updatedSession) {
        GrupyTerminyTreningowId id = new GrupyTerminyTreningowId(grupaId, terminId);
        return grupyTerminyTreningowRepository.findById(id)
                .map(existingSession -> {
                    existingSession.setGrupa(updatedSession.getGrupa());
                    existingSession.setTermin(updatedSession.getTermin());
                    return ResponseEntity.ok(grupyTerminyTreningowRepository.save(existingSession));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a group training session
    @DeleteMapping("/{grupaId}/{terminId}")
    public ResponseEntity<?> deleteGroupTrainingSession(@PathVariable Integer grupaId, @PathVariable Integer terminId) {
        GrupyTerminyTreningowId id = new GrupyTerminyTreningowId(grupaId, terminId);
        return grupyTerminyTreningowRepository.findById(id)
                .map(session -> {
                    grupyTerminyTreningowRepository.delete(session);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
