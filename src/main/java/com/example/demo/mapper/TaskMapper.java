package com.example.demo.mapper;

import java.util.List;

import com.example.demo.dto.AnswerResponse;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;

public class TaskMapper {

    public static TaskResponse mapToResponse(Task task) {
        User user = task.getUser();
        UserResponse userResponse = new UserResponse(user.getId(), user.getName());

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
