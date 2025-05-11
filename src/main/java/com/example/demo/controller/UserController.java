package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // GET all users as DTOs
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO) // Преобразуем каждый объект User в UserDTO
                .collect(Collectors.toList());
    }


    // GET one user by ID as DTO
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        return userRepository.findById(userId)
                .map(this::mapToDTO) // Преобразуем найденного пользователя в DTO
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new user with DTO
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User user = mapToEntity(userDTO); // Преобразуем DTO в сущность
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser); // Возвращаем DTO
    }

    // PUT to update an existing user using DTO
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer userId, @RequestBody UserDTO updatedUserDTO) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setLogin(updatedUserDTO.getLogin());
                    existingUser.setRole(updatedUserDTO.getRole());
                    // Примечание: поле passwordHash здесь не обновляется, так как оно отсутствует в DTO
                    User updatedUser = userRepository.save(existingUser);
                    return ResponseEntity.ok(mapToDTO(updatedUser)); // Возвращаем обновленный DTO
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT to update a user's password
    @PutMapping("/{userId}/password")
    public ResponseEntity<?> updatePassword(@PathVariable Integer userId, @RequestBody String newPassword) {
        return userRepository.findById(userId)
                .map(user -> {
                    // Хэшируем новый пароль
                    String hashedPassword = hashPassword(newPassword);
                    user.setPasswordHash(hashedPassword);
                    userRepository.save(user);
                    return ResponseEntity.ok("Password updated successfully.");
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a user by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByLoginAndCheckPassword")
    public ResponseEntity<Boolean> findByLoginAndCheckPassword(@RequestParam String username, @RequestParam String password) {
        return userRepository.findByLogin(username)
                .map(user -> {
                    boolean isPasswordMatch = checkPassword(password, user.getPasswordHash());
                    return ResponseEntity.ok(isPasswordMatch);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setLogin(user.getLogin());
        dto.setRole(user.getRole());
        return dto;
    }

    private User mapToEntity(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setLogin(dto.getLogin());
        user.setRole(dto.getRole());
        return user;
    }

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

}
