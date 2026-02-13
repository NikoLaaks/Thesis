package com.example.demo.mapper;

import com.example.demo.dto.AnswerResponse;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.Answer;
import com.example.demo.entity.User;

public class AnswerMapper {

    public static AnswerResponse mapToResponse(Answer answer) {
        User user = answer.getUser();
        UserResponse userResponse = new UserResponse(user.getId(), user.getName());
        return new AnswerResponse(
                answer.getId(),
                answer.getContent(),
                answer.getTask().getId(),
                userResponse);
    }
}
