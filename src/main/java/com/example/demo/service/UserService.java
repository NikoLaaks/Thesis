package com.example.demo.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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
        String hashed = passwordEncoder.encode(dto.getPassword());
        user.setPassword(hashed);
        user.setName(dto.getName());

        Set<Role> roles = dto.getRoles()
                .stream()
                .map(roleName -> roleRepository.findByName(roleName).orElseThrow())
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userRepository.save(user);
    }

    // Poista käyttäjä
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
