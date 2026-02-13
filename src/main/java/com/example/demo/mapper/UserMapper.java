package com.example.demo.mapper;

import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;

public class UserMapper {
    public static UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName());
    }
}
