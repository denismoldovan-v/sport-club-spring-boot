package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.GrupyZawodnicy;
import com.example.demo.repository.GrupyZawodnicyRepository;

import java.util.List;

@RestController
@RequestMapping("/api/grupyZawodnicy")
public class GrupyZawodnicyController {

    @Autowired
    private GrupyZawodnicyRepository grupyZawodnicyRepository;

    // GET all entries
    @GetMapping
    public List<GrupyZawodnicy> getAllEntries() {
        return grupyZawodnicyRepository.findAll();
    }

    // GET one entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<GrupyZawodnicy> getEntryById(@PathVariable Long id) {
        return grupyZawodnicyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST a new entry
    @PostMapping
    public GrupyZawodnicy createEntry(@RequestBody GrupyZawodnicy newEntry) {
        return grupyZawodnicyRepository.save(newEntry);
    }

    // PUT to update an existing entry
    @PutMapping("/{id}")
    public ResponseEntity<GrupyZawodnicy> updateEntry(@PathVariable Long id, @RequestBody GrupyZawodnicy updatedEntry) {
        return grupyZawodnicyRepository.findById(id)
                .map(existingEntry -> {
                    // Update relationships
                    existingEntry.setGrupa(updatedEntry.getGrupa());
                    existingEntry.setZawodnik(updatedEntry.getZawodnik());

                    // Update other fields
                    existingEntry.setDataPrzypisania(updatedEntry.getDataPrzypisania());
                    existingEntry.setDataWypisania(updatedEntry.getDataWypisania());

                    return ResponseEntity.ok(grupyZawodnicyRepository.save(existingEntry));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // DELETE an entry
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable Long id) {
        return grupyZawodnicyRepository.findById(id)
                .map(entry -> {
                    grupyZawodnicyRepository.delete(entry);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
