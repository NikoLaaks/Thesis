package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    // 1. HAE KAIKKI
    @GetMapping
    public List<Task> getAll() {
        return repository.findAll();
    }

    // 2. LISÄÄ UUSI
    @PostMapping
    public Task create(@RequestBody Task task) {
        return repository.save(task);
    }

    // 3. PÄIVITÄ
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = repository.findById(id).orElseThrow();
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        return repository.save(task);
    }

    // 4. POISTA
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
