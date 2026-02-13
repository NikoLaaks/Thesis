package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AnswerRequest;
import com.example.demo.dto.AnswerResponse;
import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.mapper.AnswerMapper;
import com.example.demo.mapper.TaskMapper;
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
    public List<TaskResponse> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper::mapToResponse)
                .toList();
    }

    // Hae taski ID:n perusteella
    public TaskResponse getTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        return TaskMapper.mapToResponse(task);
    }

    // Lisää uusi taski
    public void createTask(TaskRequest dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setUser(user);
        taskRepository.save(task);
    }

    // Päivitä taski
    public void updateTask(Long id, TaskRequest dto) {
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
    public void addAnswerToTask(Long id, AnswerRequest dto) {
        Task task = taskRepository.findById(id).orElseThrow();
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Answer answer = new Answer();
        answer.setContent(dto.getContent());
        answer.setUser(user);
        answer.setTask(task);
        answerRepository.save(answer);
    }

    // Hae taskiin kuuluvat vastaukset
    public List<AnswerResponse> getAnswerByTask(Long id) {
        return answerRepository.findByTaskId(id)
                .stream()
                .map(AnswerMapper::mapToResponse)
                .toList();
    }
}
