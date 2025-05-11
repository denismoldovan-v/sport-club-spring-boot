package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Wyplaty;
import com.example.demo.repository.WyplatyRepository;

import java.util.List;

@RestController
@RequestMapping("/api/wyplaty")
public class WyplatyController {

    @Autowired
    private WyplatyRepository wyplatyRepository;

    // GET all payments
    @GetMapping
    public List<Wyplaty> getAllPayments() {
        return wyplatyRepository.findAll();
    }

    // GET one payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Wyplaty> getPaymentById(@PathVariable Integer id) {
        return wyplatyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new payment
    @PostMapping
    public Wyplaty createPayment(@RequestBody Wyplaty newPayment) {
        return wyplatyRepository.save(newPayment);
    }

    // PUT to update an existing payment
    @PutMapping("/{id}")
    public ResponseEntity<Wyplaty> updatePayment(@PathVariable Integer id, @RequestBody Wyplaty updatedPayment) {
        return wyplatyRepository.findById(id)
                .map(existingPayment -> {
                    existingPayment.setData(updatedPayment.getData());
                    existingPayment.setTytulWyplaty(updatedPayment.getTytulWyplaty());
                    existingPayment.setMetodaPlatnosci(updatedPayment.getMetodaPlatnosci());
                    existingPayment.setNrKonta(updatedPayment.getNrKonta());
                    existingPayment.setPracownik(updatedPayment.getPracownik());
                    return ResponseEntity.ok(wyplatyRepository.save(existingPayment));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a payment
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Integer id) {
        return wyplatyRepository.findById(id)
                .map(payment -> {
                    wyplatyRepository.delete(payment);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
