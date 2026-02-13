package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    // Hae kaikki käyttäjät
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToResponse)
                .toList();
    }

    // Luo uusi käyttäjä
    public void createUser(UserRequest dto) {
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
