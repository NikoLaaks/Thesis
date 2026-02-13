package com.example.demo.dto;

public class AnswerResponse {

    private Long id;
    private String content;
    private Long taskId;
    private UserResponse user;

    public AnswerResponse(Long id, String content, Long taskId, UserResponse user) {
        this.id = id;
        this.content = content;
        this.taskId = taskId;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getTaskId() {
        return taskId;
    }

    public UserResponse getUser() {
        return user;
    }

}
