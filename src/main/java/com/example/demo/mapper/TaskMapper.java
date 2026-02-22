package com.example.demo.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.dto.AnswerResponse;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;

public class TaskMapper {

    public static TaskResponse mapToResponse(Task task) {
        User user = task.getUser();
        Set<RoleName> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                roles);

        List<AnswerResponse> answerResponses = task.getAnswers()
                .stream()
                .map(AnswerMapper::mapToResponse)
                .toList();

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                userResponse,
                answerResponses);
    }
}
