package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Pracownicy;
import com.example.demo.repository.PracownicyRepository;

import java.util.List;

@RestController
@RequestMapping("/api/pracownicy")
public class PracownicyController {

    @Autowired
    private PracownicyRepository pracownicyRepository;

    // GET all employees
    @GetMapping
    public List<Pracownicy> getAllEmployees() {
        return pracownicyRepository.findAll();
    }

    // GET one employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Pracownicy> getEmployeeById(@PathVariable Integer id) {
        return pracownicyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new employee
    @PostMapping
    public Pracownicy createEmployee(@RequestBody Pracownicy newEmployee) {
        return pracownicyRepository.save(newEmployee);
    }

    // PUT to update an existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Pracownicy> updateEmployee(@PathVariable Integer id, @RequestBody Pracownicy updatedEmployee) {
        return pracownicyRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setImie(updatedEmployee.getImie());
                    existingEmployee.setNazwisko(updatedEmployee.getNazwisko());
                    existingEmployee.setTelefon(updatedEmployee.getTelefon());
                    existingEmployee.setPesel(updatedEmployee.getPesel());
                    existingEmployee.setDataUrodzenia(updatedEmployee.getDataUrodzenia());
                    existingEmployee.setDataZatrudnienia(updatedEmployee.getDataZatrudnienia());
                    existingEmployee.setPensja(updatedEmployee.getPensja());
                    existingEmployee.setDataZwolnienia(updatedEmployee.getDataZwolnienia());
                    existingEmployee.setKlub(updatedEmployee.getKlub());
                    existingEmployee.setAdres(updatedEmployee.getAdres());
                    return ResponseEntity.ok(pracownicyRepository.save(existingEmployee));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        return pracownicyRepository.findById(id)
                .map(employee -> {
                    pracownicyRepository.delete(employee);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
