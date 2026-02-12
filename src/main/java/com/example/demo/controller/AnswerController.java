package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Answer;
import com.example.demo.service.AnswerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService service;

    public AnswerController(AnswerService service) {
        this.service = service;
    }

    // Vastauksen haku ID:llä
    @GetMapping("/{id}")
    public Answer getAnswer(@PathVariable Long id) {
        return service.getAnswerById(id);
    }

    // Vastauksen muokkaus
    @PutMapping("/{id}")
    public Answer update(@PathVariable Long id, @RequestBody Answer answerDetails) {
        return service.updateAnswer(id, answerDetails);
    }

    @GetMapping("/my")
    public List<Answer> getAnswersByUser(@RequestParam Long userId) {
        return service.getAnswersByUser(userId);
    }
}
