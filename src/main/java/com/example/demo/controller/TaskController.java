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

import com.example.demo.entity.Answer;
import com.example.demo.entity.Task;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AnswerRepository answerRepository;

    // Hae kaikki taskit
    @GetMapping
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Lisää uusi taski
    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // Päivitä taski
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        return taskRepository.save(task);
    }

    // Poista taski
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    // Lisää uusi vastaus
    @PostMapping("{id}/answers")
    public Answer addAnswerToTask(@PathVariable Long id, @RequestBody Answer answer) {
        Task task = taskRepository.findById(id).orElseThrow();
        answer.setTask(task);
        return answerRepository.save(answer);
    }

    @GetMapping("/{id}/answers")
    public List<Answer> getAnswersByTask(@PathVariable Long id) {
        return answerRepository.findByTaskId(id);
    }

}
