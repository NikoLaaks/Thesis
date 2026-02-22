package com.example.demo.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.dto.AnswerResponse;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.User;

public class AnswerMapper {

    public static AnswerResponse mapToResponse(Answer answer) {
        User user = answer.getUser();

        Set<RoleName> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                roles);
        return new AnswerResponse(
                answer.getId(),
                answer.getContent(),
                answer.getTask().getId(),
                userResponse);
    }
}
