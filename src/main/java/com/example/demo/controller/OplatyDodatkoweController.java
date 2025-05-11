package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.OplatyDodatkowe;
import com.example.demo.repository.OplatyDodatkoweRepository;

import java.util.List;

@RestController
@RequestMapping("/api/oplatyDodatkowe")
public class OplatyDodatkoweController {

    @Autowired
    private OplatyDodatkoweRepository oplatyDodatkoweRepository;

    // GET all additional fees
    @GetMapping
    public List<OplatyDodatkowe> getAllAdditionalFees() {
        return oplatyDodatkoweRepository.findAll();
    }

    // GET one additional fee by ID
    @GetMapping("/{id}")
    public ResponseEntity<OplatyDodatkowe> getAdditionalFeeById(@PathVariable Integer id) {
        return oplatyDodatkoweRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new additional fee
    @PostMapping
    public OplatyDodatkowe createAdditionalFee(@RequestBody OplatyDodatkowe newFee) {
        return oplatyDodatkoweRepository.save(newFee);
    }

    // PUT to update an existing additional fee
    @PutMapping("/{id}")
    public ResponseEntity<OplatyDodatkowe> updateAdditionalFee(@PathVariable Integer id, @RequestBody OplatyDodatkowe updatedFee) {
        return oplatyDodatkoweRepository.findById(id)
                .map(existingFee -> {
                    existingFee.setRozmiar(updatedFee.getRozmiar());
                    existingFee.setData(updatedFee.getData());
                    existingFee.setTypOplaty(updatedFee.getTypOplaty());
                    existingFee.setKlub(updatedFee.getKlub());
                    return ResponseEntity.ok(oplatyDodatkoweRepository.save(existingFee));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an additional fee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdditionalFee(@PathVariable Integer id) {
        return oplatyDodatkoweRepository.findById(id)
                .map(fee -> {
                    oplatyDodatkoweRepository.delete(fee);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
