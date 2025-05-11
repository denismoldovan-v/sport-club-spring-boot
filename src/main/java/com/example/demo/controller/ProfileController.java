package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserRepository userRepository;
    private final RoleUpgradeRequestRepository roleUpgradeRequestRepository;
    private final ZawodnicyRepository zawodnicyRepository;
    private final TrenerzyRepository trenerzyRepository;
    private final PracownicyRepository pracownicyRepository;
    private final AdresyRepository adresyRepository;
    private final KlubyLekkoatletyczneRepository klubyLekkoatletyczneRepository;


    public ProfileController(UserRepository userRepository,
                             RoleUpgradeRequestRepository roleUpgradeRequestRepository,
                             ZawodnicyRepository zawodnicyRepository,
                             TrenerzyRepository trenerzyRepository, PracownicyRepository pracownicyRepository, AdresyRepository adresyRepository, KlubyLekkoatletyczneRepository klubyLekkoatletyczneRepository) {
        this.userRepository = userRepository;
        this.roleUpgradeRequestRepository = roleUpgradeRequestRepository;
        this.zawodnicyRepository = zawodnicyRepository;
        this.trenerzyRepository = trenerzyRepository;
        this.pracownicyRepository = pracownicyRepository;
        this.adresyRepository = adresyRepository;
        this.klubyLekkoatletyczneRepository = klubyLekkoatletyczneRepository;
    }

    @GetMapping
    public String profile(Authentication authentication, Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size) {
        if (authentication == null) {
            return "redirect:/login";
        }

        String username = authentication.getName();
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        // Map user entity to DTO
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setLogin(user.getLogin());
        userDTO.setRole(user.getRole());

        model.addAttribute("user", userDTO);

        // Add role requests data for admins
        if ("ADMIN".equals(user.getRole())) {
            Pageable pageable = PageRequest.of(page, size);
            Page<RoleUpgradeRequest> requestsPage = roleUpgradeRequestRepository.findByStatus("PENDING", pageable);

            model.addAttribute("requests", requestsPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", requestsPage.getTotalPages());
            model.addAttribute("totalElements", requestsPage.getTotalElements());
            model.addAttribute("pageSize", size);
        }

        return "user/profile";
    }

    @PostMapping("/role-requests")
    public String createRoleRequest(@RequestParam String requestedRole,
                                    @RequestParam(required = false) String imie,
                                    @RequestParam(required = false) String nazwisko,
                                    @RequestParam(required = false) String numerTelefonu,
                                    @RequestParam(required = false) String dataUrodzenia,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false) String pesel,
                                    @RequestParam(required = false) String dataZatrudnienia,
                                    @RequestParam(required = false) Integer klubId,
                                    @RequestParam(required = false) Integer adresId,
                                    @RequestParam(required = false) String typSportu,
                                    @RequestParam(required = false) String doswiadczenie,
                                    @RequestParam(required = false) String nrLicencji,
                                    @RequestParam(required = false) String miasto,
                                    @RequestParam(required = false) String kraj,
                                    @RequestParam(required = false) String kodPocztowy,
                                    @RequestParam(required = false) String nazwaUlicy,
                                    @RequestParam(required = false) String nrDomu,
                                    @RequestParam(required = false) String nrLokalu,
                                    Principal principal,
                                    Model model) {

        String username = principal.getName();
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Создаем запрос на изменение роли
        RoleUpgradeRequest request = new RoleUpgradeRequest();
        request.setUser(user);
        request.setRequestedRole(requestedRole);
        request.setStatus("PENDING");
        request.setRequestDate(LocalDateTime.now());
        roleUpgradeRequestRepository.save(request);

        // Создаем и сохраняем адрес, если все обязательные поля присутствуют
        Adresy adres = null;
        if (miasto != null && kraj != null && kodPocztowy != null && nazwaUlicy != null && nrDomu != null) {
            adres = new Adresy();
            adres.setMiasto(miasto);
            adres.setKraj(kraj);
            adres.setKodPocztowy(kodPocztowy);
            adres.setNazwaUlicy(nazwaUlicy);
            adres.setNrDomu(nrDomu);
            adres.setNrLokalu(nrLokalu);

            adres = adresyRepository.save(adres);
        }

        // Если пользователь выбирает роль ZAWODNIK
        if ("ZAWODNIK".equals(requestedRole)) {
            // Проверяем, есть ли уже запрос на эту роль
            if (zawodnicyRepository.getZawodnicyByUser(user).stream().findFirst().isPresent()) {
                model.addAttribute("error", "Już dostaliśmy poprzednie zgłoszenie, nie możesz znowu złożyć tą prośbę o zmiane roli!");
                return "redirect:/profile";
            }

            // Создаем нового zawodnika
            Zawodnicy zawodnik = new Zawodnicy();
            zawodnik.setImie(imie);
            zawodnik.setNazwisko(nazwisko);
            zawodnik.setNumerTelefonu(numerTelefonu);

            if (dataUrodzenia != null) {
                LocalDate localDate = LocalDate.parse(dataUrodzenia);
                Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                zawodnik.setDataUrodzenia(instant);
            }

            zawodnik.setEmail(email);
            zawodnik.setOplataSubskrypcyjna(BigDecimal.valueOf(0));
            zawodnik.setUser(user);

            zawodnicyRepository.save(zawodnik);
        }

        // Если пользователь выбирает роль TRENER
        else if ("TRENER".equals(requestedRole)) {
            // Проверяем, существует ли уже такой тренер
            if (trenerzyRepository.findByUser(user).isPresent()) {
                model.addAttribute("error", "You have already submitted a request for the TRENER role!");
                return "redirect:/profile";
            }

            // Создаем запись в таблице Pracownicy, если ее нет
            if (pracownicyRepository.findByUser(user).isEmpty()) {
                Pracownicy pracownik = new Pracownicy();
                pracownik.setUser(user);
                pracownik.setImie(imie);
                pracownik.setNazwisko(nazwisko);
                pracownik.setTelefon(numerTelefonu);
                pracownik.setPesel(pesel);
                pracownik.setPensja((double) 0);
                System.out.println(dataUrodzenia);
                System.out.println(dataZatrudnienia);
                if (dataUrodzenia != null) {
                    LocalDate localDate = LocalDate.parse(dataUrodzenia);
                    pracownik.setDataUrodzenia(localDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
                }

                if (dataZatrudnienia != null) {
                    LocalDate localDate = LocalDate.parse(dataZatrudnienia);
                    pracownik.setDataZatrudnienia(localDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
                }

                // Предположим, что клуб с ID 1 существует в системе
                pracownik.setKlub(klubyLekkoatletyczneRepository.findById(1)
                        .orElseThrow(() -> new IllegalArgumentException("Klub not found")));
                pracownik.setAdres(adres);
                pracownicyRepository.save(pracownik);
            }

            // Создаем запись для тренера
            Trenerzy trener = new Trenerzy();
            trener.setUser(user);
            trener.setTypSportu(typSportu);
            trener.setDoswiadczenie(doswiadczenie);
            trener.setNrLicencji(nrLicencji);

            trenerzyRepository.save(trener);
        }

        return "redirect:/profile?success=RequestCreated";
    }



    @PostMapping("/role-requests/{id}/approve")
    public String approveRequest(@PathVariable Integer id, Principal principal) {
        RoleUpgradeRequest request = roleUpgradeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        // Update the request status and admin information
        request.setStatus("APPROVED");
        request.setReviewDate(LocalDateTime.now());
        User admin = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        request.setReviewedBy(admin);

        // Update the user's role
        User user = request.getUser();
        user.setRole(request.getRequestedRole());
        userRepository.save(user);
        roleUpgradeRequestRepository.save(request);

        return "redirect:/profile?success=Approved";
    }

    @PostMapping("/role-requests/{id}/decline")
    public String declineRequest(@PathVariable Integer id, Principal principal) {
        RoleUpgradeRequest request = roleUpgradeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        // Update the request status and admin information
        request.setStatus("DECLINED");
        request.setReviewDate(LocalDateTime.now());
        User admin = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        request.setReviewedBy(admin);

        roleUpgradeRequestRepository.save(request);
        return "redirect:/profile?success=Declined";
    }
}
