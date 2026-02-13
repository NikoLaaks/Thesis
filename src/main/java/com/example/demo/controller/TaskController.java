package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AnswerRequest;
import com.example.demo.dto.AnswerResponse;
import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Hae kaikki taskit
    @GetMapping
    public List<TaskResponse> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    // Lisää uusi taski
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TaskRequest dto) {
        taskService.createTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Päivitä taski
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody TaskRequest dto) {
        taskService.updateTask(id, dto);
        return ResponseEntity.ok().build();
    }

    // Poista taski
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    // Lisää uusi vastaus
    @PostMapping("{id}/answers")
    public ResponseEntity<Void> addAnswerToTask(@PathVariable Long id, @RequestBody AnswerRequest dto) {
        taskService.addAnswerToTask(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/answers")
    public List<AnswerResponse> getAnswersByTask(@PathVariable Long id) {
        return taskService.getAnswerByTask(id);
    }

}
