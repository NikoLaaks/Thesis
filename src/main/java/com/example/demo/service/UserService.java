package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    // Hae kaikki käyttäjät
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Luo uusi käyttäjä
    public void createUser(UserDTO dto) {
        User user = new User();
        user.setUserName(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        userRepository.save(user);
    }

    // Poista käyttäjä
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
