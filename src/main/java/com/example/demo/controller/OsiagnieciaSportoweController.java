package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.OsiagnieciaSportowe;
import com.example.demo.repository.OsiagnieciaSportoweRepository;

import java.util.List;

@RestController
@RequestMapping("/api/osiagnieciaSportowe")
public class OsiagnieciaSportoweController {

    @Autowired
    private OsiagnieciaSportoweRepository osiagnieciaSportoweRepository;

    // GET all achievements
    @GetMapping
    public List<OsiagnieciaSportowe> getAllAchievements() {
        return osiagnieciaSportoweRepository.findAll();
    }

    // GET one achievement by ID
    @GetMapping("/{id}")
    public ResponseEntity<OsiagnieciaSportowe> getAchievementById(@PathVariable Integer id) {
        return osiagnieciaSportoweRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new achievement
    @PostMapping
    public OsiagnieciaSportowe createAchievement(@RequestBody OsiagnieciaSportowe newAchievement) {
        return osiagnieciaSportoweRepository.save(newAchievement);
    }

    // PUT to update an existing achievement
    @PutMapping("/{id}")
    public ResponseEntity<OsiagnieciaSportowe> updateAchievement(@PathVariable Integer id, @RequestBody OsiagnieciaSportowe updatedAchievement) {
        return osiagnieciaSportoweRepository.findById(id)
                .map(existingAchievement -> {
                    existingAchievement.setCzyOsiagnalMiejsce(updatedAchievement.getCzyOsiagnalMiejsce());
                    existingAchievement.setMiejsceOsiagniete(updatedAchievement.getMiejsceOsiagniete());
                    existingAchievement.setOpis(updatedAchievement.getOpis());
                    existingAchievement.setAttribute1(updatedAchievement.getAttribute1());
                    return ResponseEntity.ok(osiagnieciaSportoweRepository.save(existingAchievement));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an achievement
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAchievement(@PathVariable Integer id) {
        return osiagnieciaSportoweRepository.findById(id)
                .map(achievement -> {
                    osiagnieciaSportoweRepository.delete(achievement);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
