package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Component("taskSecurity")
public class TaskSecurity {

    private final TaskRepository taskRepository;

    public TaskSecurity(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public boolean isOwner(Long taskId, Authentication authentication) {

        Task task = taskRepository.findById(taskId).orElseThrow();

        String username = authentication.getName();

        return task.getUser().getUserName().equals(username);
    }
}
