package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.TypyOplat;
import com.example.demo.repository.TypyOplatRepository;

import java.util.List;

@RestController
@RequestMapping("/api/typyOplat")
public class TypyOplatController {

    @Autowired
    private TypyOplatRepository typyOplatRepository;

    // GET all types of fees
    @GetMapping
    public List<TypyOplat> getAllTypesOfFees() {
        return typyOplatRepository.findAll();
    }

    // GET one type of fee by ID
    @GetMapping("/{id}")
    public ResponseEntity<TypyOplat> getTypeOfFeeById(@PathVariable Integer id) {
        return typyOplatRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new type of fee
    @PostMapping
    public TypyOplat createTypeOfFee(@RequestBody TypyOplat newTypeOfFee) {
        return typyOplatRepository.save(newTypeOfFee);
    }

    // PUT to update an existing type of fee
    @PutMapping("/{id}")
    public ResponseEntity<TypyOplat> updateTypeOfFee(@PathVariable Integer id, @RequestBody TypyOplat updatedTypeOfFee) {
        return typyOplatRepository.findById(id)
                .map(existingTypeOfFee -> {
                    existingTypeOfFee.setNazwa(updatedTypeOfFee.getNazwa());
                    existingTypeOfFee.setOpis(updatedTypeOfFee.getOpis());
                    existingTypeOfFee.setOplata(updatedTypeOfFee.getOplata());
                    return ResponseEntity.ok(typyOplatRepository.save(existingTypeOfFee));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a type of fee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTypeOfFee(@PathVariable Integer id) {
        return typyOplatRepository.findById(id)
                .map(typeOfFee -> {
                    typyOplatRepository.delete(typeOfFee);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
