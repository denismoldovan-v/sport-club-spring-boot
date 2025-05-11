package com.example.demo.controller;

import com.example.demo.model.Grupy;
import com.example.demo.model.GrupyZawodnicy;
import com.example.demo.model.Zawodnicy;
import com.example.demo.repository.GrupyRepository;
import com.example.demo.repository.GrupyZawodnicyRepository;
import com.example.demo.repository.ZawodnicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/zawodnicy")
public class ZawodnicyController {

    @Autowired
    private GrupyRepository grupyRepository;

    @Autowired
    private GrupyZawodnicyRepository grupyZawodnicyRepository;

    @Autowired
    private ZawodnicyRepository zawodnicyRepository;

    // Get all groups
    @GetMapping("/groups")
    public ResponseEntity<List<Grupy>> getAllGroups() {
        return ResponseEntity.ok(grupyRepository.findAll());
    }

    // Get details of a specific group
    @GetMapping("/groups/{groupId}")
    public ResponseEntity<?> getGroupDetails(@PathVariable Integer groupId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return grupyRepository.findById(groupId)
                .map(group -> {
                    List<GrupyZawodnicy> participants = grupyZawodnicyRepository.findByGrupaId((long) group.getId());
                    List<ParticipantResponse> participantResponses = participants.stream().map(gz -> {
                        Zawodnicy zawodnik = gz.getZawodnik();
                        return new ParticipantResponse(
                                zawodnik.getImie() + " " + zawodnik.getNazwisko(),
                                gz.getDataPrzypisania()
                        );
                    }).collect(Collectors.toList());

                    GroupResponse response = new GroupResponse(
                            group.getNazwa(),
                            group.getTypSportu(),
                            participantResponses
                    );
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DTOs for API responses
    public static class GroupResponse {
        private String nazwa;
        private String typSportu;
        private List<ParticipantResponse> participants;

        public GroupResponse(String nazwa, String typSportu, List<ParticipantResponse> participants) {
            this.nazwa = nazwa;
            this.typSportu = typSportu;
            this.participants = participants;
        }

        public String getNazwa() {
            return nazwa;
        }

        public void setNazwa(String nazwa) {
            this.nazwa = nazwa;
        }

        public String getTypSportu() {
            return typSportu;
        }

        public void setTypSportu(String typSportu) {
            this.typSportu = typSportu;
        }

        public List<ParticipantResponse> getParticipants() {
            return participants;
        }

        public void setParticipants(List<ParticipantResponse> participants) {
            this.participants = participants;
        }
    }

    public static class ParticipantResponse {
        private String name;
        private String dataPrzypisania;

        public ParticipantResponse(String name, Instant dataPrzypisania) {
            this.name = name;
            this.dataPrzypisania = dataPrzypisania.toString(); // Convert Instant to String
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDataPrzypisania() {
            return dataPrzypisania;
        }

        public void setDataPrzypisania(String dataPrzypisania) {
            this.dataPrzypisania = dataPrzypisania;
        }
    }

}
