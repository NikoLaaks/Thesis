package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.entity.Answer;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AnswerDTO;
import com.example.demo.service.TaskDTO;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;

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
    public ResponseEntity<Void> create(@RequestBody TaskDTO dto) {
        // Haetaan käyttäjä tietokannasta ID:n perusteella, joka tulee JSON:sta
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Käyttäjää ei löytynyt"));
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());

        // Asetetaan haettu käyttäjä taskille
        task.setUser(user);
        taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Päivitä taski
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody TaskDTO dto) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        taskRepository.save(task);

        return ResponseEntity.ok().build();
    }

    // Poista taski
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    // Lisää uusi vastaus
    @PostMapping("{id}/answers")
    public ResponseEntity<Void> addAnswerToTask(@PathVariable Long id, @RequestBody AnswerDTO dto) {
        Task task = taskRepository.findById(id).orElseThrow();
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Käyttäjää ei löytynyt"));
        Answer answer = new Answer();
        answer.setContent(dto.getContent());

        answer.setUser(user);

        answer.setTask(task);
        answerRepository.save(answer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/answers")
    public List<Answer> getAnswersByTask(@PathVariable Long id) {
        return answerRepository.findByTaskId(id);
    }

}
