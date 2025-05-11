package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.KlubZawodnik;
import com.example.demo.model.KlubZawodnikId;
import com.example.demo.repository.KlubZawodnikRepository;

import java.util.List;

@RestController
@RequestMapping("/api/klubZawodnik")
public class KlubZawodnikController {

    @Autowired
    private KlubZawodnikRepository klubZawodnikRepository;

    // GET all relationships
    @GetMapping
    public List<KlubZawodnik> getAllRelationships() {
        return klubZawodnikRepository.findAll();
    }

    // GET a specific relationship by klubId and zawodnikId
    @GetMapping("/{klubId}/{zawodnikId}")
    public ResponseEntity<KlubZawodnik> getRelationshipById(@PathVariable Integer klubId, @PathVariable Integer zawodnikId) {
        KlubZawodnikId id = new KlubZawodnikId(zawodnikId, klubId);
        return klubZawodnikRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new relationship
    @PostMapping
    public KlubZawodnik createRelationship(@RequestBody KlubZawodnik newKlubZawodnik) {
        return klubZawodnikRepository.save(newKlubZawodnik);
    }

    // PUT to update an existing relationship
    @PutMapping("/{klubId}/{zawodnikId}")
    public ResponseEntity<KlubZawodnik> updateRelationship(@PathVariable Integer klubId, @PathVariable Integer zawodnikId, @RequestBody KlubZawodnik updatedKlubZawodnik) {
        KlubZawodnikId id = new KlubZawodnikId(zawodnikId, klubId);
        return klubZawodnikRepository.findById(id)
                .map(existingKlubZawodnik -> {
                    existingKlubZawodnik.setZawodnik(updatedKlubZawodnik.getZawodnik());
                    existingKlubZawodnik.setKlub(updatedKlubZawodnik.getKlub());
                    return ResponseEntity.ok(klubZawodnikRepository.save(existingKlubZawodnik));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a relationship
    @DeleteMapping("/{klubId}/{zawodnikId}")
    public ResponseEntity<Void> deleteRelationship(@PathVariable Integer klubId, @PathVariable Integer zawodnikId) {
        KlubZawodnikId id = new KlubZawodnikId(zawodnikId, klubId);
        return klubZawodnikRepository.findById(id)
                .map(klubZawodnik -> {
                    klubZawodnikRepository.delete(klubZawodnik);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
