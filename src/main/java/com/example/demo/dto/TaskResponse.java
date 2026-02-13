package com.example.demo.dto;

import java.util.List;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private UserResponse user;
    private List<AnswerResponse> answers;

    public TaskResponse(Long id, String title, String description, UserResponse user, List<AnswerResponse> answers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UserResponse getUser() {
        return user;
    }

    public List<AnswerResponse> getAnswers() {
        return answers;
    }

}
