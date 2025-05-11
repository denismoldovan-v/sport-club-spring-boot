package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Grupy;
import com.example.demo.repository.GrupyRepository;

import java.util.List;

@RestController
@RequestMapping("/api/grupy")
public class GrupyController {

    @Autowired
    private GrupyRepository grupyRepository;

    // GET all groups
    @GetMapping
    public List<Grupy> getAllGroups() {
        return grupyRepository.findAll();
    }

    // GET one group by ID
    @GetMapping("/{id}")
    public ResponseEntity<Grupy> getGroupById(@PathVariable Integer id) {
        return grupyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST a new group
    @PostMapping
    public Grupy createGroup(@RequestBody Grupy newGroup) {
        return grupyRepository.save(newGroup);
    }

    // PUT update an existing group
    @PutMapping("/{id}")
    public ResponseEntity<Grupy> updateGroup(@PathVariable Integer id, @RequestBody Grupy updatedGroup) {
        return grupyRepository.findById(id)
                .map(existingGroup -> {
                    existingGroup.setNazwa(updatedGroup.getNazwa());
                    existingGroup.setTypSportu(updatedGroup.getTypSportu());
                    existingGroup.setOpis(updatedGroup.getOpis());
                    existingGroup.setKlub(updatedGroup.getKlub());
                    existingGroup.setTrener(updatedGroup.getTrener());
                    return ResponseEntity.ok(grupyRepository.save(existingGroup));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a group
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Integer id) {
        return grupyRepository.findById(id)
                .map(existingGroup -> {
                    grupyRepository.delete(existingGroup);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
