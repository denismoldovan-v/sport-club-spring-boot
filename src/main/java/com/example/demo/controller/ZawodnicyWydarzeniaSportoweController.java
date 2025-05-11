package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.ZawodnicyWydarzeniaSportowe;
import com.example.demo.repository.ZawodnicyWydarzeniaSportoweRepository;

import java.util.List;

@RestController
@RequestMapping("/api/zawodnicyWydarzeniaSportowe")
public class ZawodnicyWydarzeniaSportoweController {

    @Autowired
    private ZawodnicyWydarzeniaSportoweRepository zawodnicyWydarzeniaSportoweRepository;

    // GET all athlete-event associations
    @GetMapping
    public List<ZawodnicyWydarzeniaSportowe> getAllAssociations() {
        return zawodnicyWydarzeniaSportoweRepository.findAll();
    }

    // GET one association by ID
    @GetMapping("/{id}")
    public ResponseEntity<ZawodnicyWydarzeniaSportowe> getAssociationById(@PathVariable Long id) {
        return zawodnicyWydarzeniaSportoweRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new association
    @PostMapping
    public ZawodnicyWydarzeniaSportowe createAssociation(@RequestBody ZawodnicyWydarzeniaSportowe newAssociation) {
        return zawodnicyWydarzeniaSportoweRepository.save(newAssociation);
    }

    // PUT to update an existing association
    @PutMapping("/{id}")
    public ResponseEntity<ZawodnicyWydarzeniaSportowe> updateAssociation(@PathVariable Long id, @RequestBody ZawodnicyWydarzeniaSportowe updatedAssociation) {
        return zawodnicyWydarzeniaSportoweRepository.findById(id)
                .map(existingAssociation -> {
                    existingAssociation.setZawodnikId(updatedAssociation.getZawodnikId());
                    existingAssociation.setWydarzenieId(updatedAssociation.getWydarzenieId());
                    existingAssociation.setOsiagniecie(updatedAssociation.getOsiagniecie());
                    return ResponseEntity.ok(zawodnicyWydarzeniaSportoweRepository.save(existingAssociation));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an association
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssociation(@PathVariable Long id) {
        return zawodnicyWydarzeniaSportoweRepository.findById(id)
                .map(association -> {
                    zawodnicyWydarzeniaSportoweRepository.delete(association);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
