package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.PracownicyBiura;
import com.example.demo.repository.PracownicyBiuraRepository;

import java.util.List;

@RestController
@RequestMapping("/api/pracownicyBiura")
public class PracownicyBiuraController {

    @Autowired
    private PracownicyBiuraRepository pracownicyBiuraRepository;

    // GET all office employees
    @GetMapping
    public List<PracownicyBiura> getAllEmployees() {
        return pracownicyBiuraRepository.findAll();
    }

    // GET one office employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<PracownicyBiura> getEmployeeById(@PathVariable Integer id) {
        return pracownicyBiuraRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new office employee
    @PostMapping
    public PracownicyBiura createEmployee(@RequestBody PracownicyBiura newEmployee) {
        return pracownicyBiuraRepository.save(newEmployee);
    }

    // PUT to update an existing office employee
    @PutMapping("/{id}")
    public ResponseEntity<PracownicyBiura> updateEmployee(@PathVariable Integer id, @RequestBody PracownicyBiura updatedEmployee) {
        return pracownicyBiuraRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setBiuroId(updatedEmployee.getBiuroId());
                    existingEmployee.setPoziomDostepu(updatedEmployee.getPoziomDostepu());
                    existingEmployee.setNumerBiurka(updatedEmployee.getNumerBiurka());
                    return ResponseEntity.ok(pracownicyBiuraRepository.save(existingEmployee));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an office employee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        return pracownicyBiuraRepository.findById(id)
                .map(employee -> {
                    pracownicyBiuraRepository.delete(employee);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
