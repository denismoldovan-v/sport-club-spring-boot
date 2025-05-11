package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.OplatySubskrypcyjne;
import com.example.demo.repository.OplatySubskrypcyjneRepository;

import java.util.List;

@RestController
@RequestMapping("/api/oplatySubskrypcyjne")
public class OplatySubskrypcyjneController {

    @Autowired
    private OplatySubskrypcyjneRepository oplatySubskrypcyjneRepository;

    // GET all subscription fees
    @GetMapping
    public List<OplatySubskrypcyjne> getAllSubscriptionFees() {
        return oplatySubskrypcyjneRepository.findAll();
    }

    // GET one subscription fee by ID
    @GetMapping("/{id}")
    public ResponseEntity<OplatySubskrypcyjne> getSubscriptionFeeById(@PathVariable Integer id) {
        return oplatySubskrypcyjneRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new subscription fee
    @PostMapping
    public OplatySubskrypcyjne createSubscriptionFee(@RequestBody OplatySubskrypcyjne newFee) {
        return oplatySubskrypcyjneRepository.save(newFee);
    }

    // PUT to update an existing subscription fee
    @PutMapping("/{id}")
    public ResponseEntity<OplatySubskrypcyjne> updateSubscriptionFee(@PathVariable Integer id, @RequestBody OplatySubskrypcyjne updatedFee) {
        return oplatySubskrypcyjneRepository.findById(id)
                .map(existingFee -> {
                    existingFee.setPoczatekSubskrypcji(updatedFee.getPoczatekSubskrypcji());
                    existingFee.setKoniecSubskrypcji(updatedFee.getKoniecSubskrypcji());
                    existingFee.setRozmiarOplaty(updatedFee.getRozmiarOplaty());
                    existingFee.setTypSubskrypcji(updatedFee.getTypSubskrypcji());
                    existingFee.setZawodnik(updatedFee.getZawodnik());
                    return ResponseEntity.ok(oplatySubskrypcyjneRepository.save(existingFee));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a subscription fee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubscriptionFee(@PathVariable Integer id) {
        return oplatySubskrypcyjneRepository.findById(id)
                .map(fee -> {
                    oplatySubskrypcyjneRepository.delete(fee);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
