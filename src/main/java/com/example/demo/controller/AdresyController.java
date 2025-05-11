package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Adresy;
import com.example.demo.repository.AdresyRepository;

import java.util.List;

@RestController
@RequestMapping("/api/adresy")
public class AdresyController {

    @Autowired
    private AdresyRepository adresyRepository;

    // GET all addresses
    @GetMapping
    public List<Adresy> getAllAddresses() {
        return adresyRepository.findAll();
    }

    // GET one address by ID
    @GetMapping("/{id}")
    public ResponseEntity<Adresy> getAddressById(@PathVariable Integer id) {
        return adresyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new address
    @PostMapping
    public Adresy createAddress(@RequestBody Adresy newAddress) {
        return adresyRepository.save(newAddress);
    }

    // PUT to update an existing address
    @PutMapping("/{id}")
    public ResponseEntity<Adresy> updateAddress(@PathVariable Integer id, @RequestBody Adresy updatedAddress) {
        return adresyRepository.findById(id)
                .map(existingAddress -> {
                    existingAddress.setMiasto(updatedAddress.getMiasto());
                    existingAddress.setKraj(updatedAddress.getKraj());
                    existingAddress.setKodPocztowy(updatedAddress.getKodPocztowy());
                    existingAddress.setNazwaUlicy(updatedAddress.getNazwaUlicy());
                    existingAddress.setNrDomu(updatedAddress.getNrDomu());
                    existingAddress.setNrLokalu(updatedAddress.getNrLokalu());
                    return ResponseEntity.ok(adresyRepository.save(existingAddress));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an address
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id) {
        return adresyRepository.findById(id)
                .map(address -> {
                    adresyRepository.delete(address);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
