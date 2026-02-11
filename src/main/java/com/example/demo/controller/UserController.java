package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Hae kaikki käyttäjät
    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Luo uusi käyttäjä
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO dto) {
        User user = new User();

        user.setUserName(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Poista käyttäjä
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

}
