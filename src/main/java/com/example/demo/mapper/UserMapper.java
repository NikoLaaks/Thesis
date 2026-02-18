package com.example.demo.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;

public class UserMapper {
    public static UserResponse mapToResponse(User user) {

        Set<String> roleNames = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());

        return new UserResponse(
                user.getId(),
                user.getName(),
                roleNames);
    }
}
