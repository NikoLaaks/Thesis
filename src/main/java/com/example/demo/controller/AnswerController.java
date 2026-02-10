package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Answer;
import com.example.demo.repository.AnswerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository repository;

    // Vastauksen haku ID:llä
    @GetMapping("/{id}")
    public Answer getAnswer(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // Vastauksen muokkaus
    @PutMapping("/{id}")
    public Answer update(@PathVariable Long id, @RequestBody Answer answerDetails) {
        Answer answer = repository.findById(id).orElseThrow();
        answer.setContent(answerDetails.getContent());
        return repository.save(answer);
    }

    @GetMapping("/my")
    public List<Answer> getAnswersByUser(@RequestParam Long userId) {
        return repository.findByUserId(userId);
    }
}
