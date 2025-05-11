package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.KlubyLekkoatletyczne;
import com.example.demo.repository.KlubyLekkoatletyczneRepository;

import java.util.List;

@RestController
@RequestMapping("/api/klubyLekkoatletyczne")
public class KlubyLekkoatletyczneController {

    @Autowired
    private KlubyLekkoatletyczneRepository klubyLekkoatletyczneRepository;

    // GET all athletic clubs
    @GetMapping
    public List<KlubyLekkoatletyczne> getAllClubs() {
        return klubyLekkoatletyczneRepository.findAll();
    }

    // GET one athletic club by ID
    @GetMapping("/{id}")
    public ResponseEntity<KlubyLekkoatletyczne> getClubById(@PathVariable Integer id) {
        return klubyLekkoatletyczneRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST a new athletic club
    @PostMapping
    public KlubyLekkoatletyczne createClub(@RequestBody KlubyLekkoatletyczne newClub) {
        return klubyLekkoatletyczneRepository.save(newClub);
    }

    // PUT to update an existing athletic club
    @PutMapping("/{id}")
    public ResponseEntity<KlubyLekkoatletyczne> updateClub(@PathVariable Integer id, @RequestBody KlubyLekkoatletyczne updatedClub) {
        return klubyLekkoatletyczneRepository.findById(id)
                .map(existingClub -> {
                    existingClub.setNazwa(updatedClub.getNazwa());
                    existingClub.setDataZalozenia(updatedClub.getDataZalozenia());
                    existingClub.setEmail(updatedClub.getEmail());
                    existingClub.setZarzadzca(updatedClub.getZarzadzca());
                    existingClub.setAdres(updatedClub.getAdres());
                    return ResponseEntity.ok(klubyLekkoatletyczneRepository.save(existingClub));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an athletic club
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClub(@PathVariable Integer id) {
        return klubyLekkoatletyczneRepository.findById(id)
                .map(club -> {
                    klubyLekkoatletyczneRepository.delete(club);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
