package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.DoswiadczeniaSportowe;
import com.example.demo.repository.DoswiadczeniaSportoweRepository;

import java.util.List;

@RestController
@RequestMapping("/api/doswiadczeniaSportowe")
public class DoswiadczeniaSportoweController {

    @Autowired
    private DoswiadczeniaSportoweRepository doswiadczeniaSportoweRepository;

    // GET all sports experiences
    @GetMapping
    public List<DoswiadczeniaSportowe> getAllExperiences() {
        return doswiadczeniaSportoweRepository.findAll();
    }

    // GET a single sports experience by ID
    @GetMapping("/{id}")
    public ResponseEntity<DoswiadczeniaSportowe> getExperienceById(@PathVariable Integer id) {
        return doswiadczeniaSportoweRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST a new sports experience
    @PostMapping
    public DoswiadczeniaSportowe createExperience(@RequestBody DoswiadczeniaSportowe doswiadczeniaSportowe) {
        // Ensure that the start date is before the end date
        if (doswiadczeniaSportowe.getDataZakonczenia() != null && doswiadczeniaSportowe.getDataRozpoczecia().isAfter(doswiadczeniaSportowe.getDataZakonczenia())) {
            throw new IllegalArgumentException("Data rozpoczęcia must be before data zakończenia.");
        }
        return doswiadczeniaSportoweRepository.save(doswiadczeniaSportowe);
    }

    // PUT to update an existing sports experience
    @PutMapping("/{id}")
    public ResponseEntity<DoswiadczeniaSportowe> updateExperience(@PathVariable Integer id, @RequestBody DoswiadczeniaSportowe doswiadczeniaSportowe) {
        return doswiadczeniaSportoweRepository.findById(id)
                .map(existingExperience -> {
                    existingExperience.setNazwa(doswiadczeniaSportowe.getNazwa());
                    existingExperience.setOpis(doswiadczeniaSportowe.getOpis());
                    existingExperience.setDataRozpoczecia(doswiadczeniaSportowe.getDataRozpoczecia());
                    existingExperience.setDataZakonczenia(doswiadczeniaSportowe.getDataZakonczenia());
                    existingExperience.setPracownik(doswiadczeniaSportowe.getPracownik());
                    return ResponseEntity.ok(doswiadczeniaSportoweRepository.save(existingExperience));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a sports experience
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable Integer id) {
        return doswiadczeniaSportoweRepository.findById(id)
                .map(experience -> {
                    doswiadczeniaSportoweRepository.delete(experience);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
