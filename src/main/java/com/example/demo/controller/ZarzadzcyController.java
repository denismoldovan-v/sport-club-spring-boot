package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Zarzadzcy;
import com.example.demo.repository.ZarzadzcyRepository;

import java.util.List;

@RestController
@RequestMapping("/api/zarzadzcy")
public class ZarzadzcyController {

    @Autowired
    private ZarzadzcyRepository zarzadzcyRepository;

    // GET all managers
    @GetMapping
    public List<Zarzadzcy> getAllManagers() {
        return zarzadzcyRepository.findAll();
    }

    // GET one manager by ID
    @GetMapping("/{id}")
    public ResponseEntity<Zarzadzcy> getManagerById(@PathVariable Integer id) {
        return zarzadzcyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new manager
    @PostMapping
    public Zarzadzcy createManager(@RequestBody Zarzadzcy newManager) {
        return zarzadzcyRepository.save(newManager);
    }

    // PUT to update an existing manager
    @PutMapping("/{id}")
    public ResponseEntity<Zarzadzcy> updateManager(@PathVariable Integer id, @RequestBody Zarzadzcy updatedManager) {
        return zarzadzcyRepository.findById(id)
                .map(existingManager -> {
                    existingManager.setImie(updatedManager.getImie());
                    existingManager.setNazwisko(updatedManager.getNazwisko());
                    existingManager.setPesel(updatedManager.getPesel());
                    existingManager.setDataUrodzenia(updatedManager.getDataUrodzenia());
                    existingManager.setDataPrzypisania(updatedManager.getDataPrzypisania());
                    return ResponseEntity.ok(zarzadzcyRepository.save(existingManager));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a manager
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteManager(@PathVariable Integer id) {
        return zarzadzcyRepository.findById(id)
                .map(manager -> {
                    zarzadzcyRepository.delete(manager);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
