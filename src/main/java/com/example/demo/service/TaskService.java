package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository,
            AnswerRepository answerRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }

    // Hae kaikki taskit
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    // Hae taski ID:n perusteella
    public Task getTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Lisää uusi taski
    public void createTask(TaskDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setUser(user);
        taskRepository.save(task);
    }

    // Päivitä taski
    public void updateTask(Long id, TaskDTO dto) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        taskRepository.save(task);
    }

    // Poista taski
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Lisää uusi vastaus
    public void addAnswerToTask(Long id, AnswerDTO dto) {
        Task task = taskRepository.findById(id).orElseThrow();
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Answer answer = new Answer();
        answer.setContent(dto.getContent());
        answer.setUser(user);
        answer.setTask(task);
        answerRepository.save(answer);
    }

    // Hae taskiin kuuluvat vastaukset
    public List<Answer> getAnswerByTask(Long id) {
        return answerRepository.findByTaskId(id);
    }

}
