package com.example.demo.controller;

import com.example.demo.dto.WydarzeniaSportoweDTO;
import com.example.demo.dto.KlubyLekkoatletyczneDTO;
import com.example.demo.model.WydarzeniaSportowe;
import com.example.demo.model.KlubyLekkoatletyczne;
import com.example.demo.repository.WydarzeniaSportoweRepository;
import com.example.demo.repository.KlubyLekkoatletyczneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wydarzeniaSportowe")
public class WydarzeniaSportoweController {

    @Autowired
    private WydarzeniaSportoweRepository wydarzeniaSportoweRepository;

    @Autowired
    private KlubyLekkoatletyczneRepository klubyLekkoatletyczneRepository;

    // GET all sports events
    @GetMapping
    public List<WydarzeniaSportoweDTO> getAllEvents() {
        return wydarzeniaSportoweRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // GET paginated sports events
    @GetMapping("/paginated")
    public Page<WydarzeniaSportoweDTO> getPaginatedEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return wydarzeniaSportoweRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    // GET one sports event by ID
    @GetMapping("/{id}")
    public ResponseEntity<WydarzeniaSportoweDTO> getEventById(@PathVariable Integer id) {
        return wydarzeniaSportoweRepository.findById(id)
                .map(this::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new sports event
    @PostMapping
    public WydarzeniaSportoweDTO createEvent(@RequestBody WydarzeniaSportoweDTO dto) {
        WydarzeniaSportowe event = mapToEntity(dto);
        WydarzeniaSportowe savedEvent = wydarzeniaSportoweRepository.save(event);
        return mapToDTO(savedEvent);
    }

    // PUT to update an existing sports event
    @PutMapping("/{id}")
    public ResponseEntity<WydarzeniaSportoweDTO> updateEvent(@PathVariable Integer id, @RequestBody WydarzeniaSportoweDTO dto) {
        return wydarzeniaSportoweRepository.findById(id)
                .map(existingEvent -> {
                    existingEvent.setData(dto.getData());
                    existingEvent.setNazwa(dto.getNazwa());
                    existingEvent.setTypSportu(dto.getTypSportu());

                    if (dto.getKlub() != null && dto.getKlub().getNazwa() != null) {
                        klubyLekkoatletyczneRepository.findByNazwa(dto.getKlub().getNazwa()).ifPresent(existingEvent::setKlub);
                    }

                    return wydarzeniaSportoweRepository.save(existingEvent);
                })
                .map(this::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a sports event
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer id) {
        if (wydarzeniaSportoweRepository.existsById(id)) {
            wydarzeniaSportoweRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Helper methods for mapping

    private WydarzeniaSportoweDTO mapToDTO(WydarzeniaSportowe event) {
        WydarzeniaSportoweDTO dto = new WydarzeniaSportoweDTO();
        dto.setId(event.getId());
        dto.setData(event.getData());
        dto.setNazwa(event.getNazwa());
        dto.setTypSportu(event.getTypSportu());
        if (event.getKlub() != null) {
            dto.setKlub(mapToDTO(event.getKlub()));
        }
        return dto;
    }

    private KlubyLekkoatletyczneDTO mapToDTO(KlubyLekkoatletyczne klub) {
        KlubyLekkoatletyczneDTO dto = new KlubyLekkoatletyczneDTO();
        dto.setId(klub.getId());
        dto.setNazwa(klub.getNazwa());
        dto.setDataZalozenia(klub.getDataZalozenia());
        dto.setEmail(klub.getEmail());
        return dto;
    }

    private WydarzeniaSportowe mapToEntity(WydarzeniaSportoweDTO dto) {
        WydarzeniaSportowe event = new WydarzeniaSportowe();
        event.setId(dto.getId());
        event.setData(dto.getData());
        event.setNazwa(dto.getNazwa());
        event.setTypSportu(dto.getTypSportu());

        if (dto.getKlub() != null && dto.getKlub().getNazwa() != null) {
            klubyLekkoatletyczneRepository.findByNazwa(dto.getKlub().getNazwa()).ifPresent(event::setKlub);
        }

        return event;
    }
}
