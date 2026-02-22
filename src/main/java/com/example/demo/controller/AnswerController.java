package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AnswerRequest;
import com.example.demo.dto.AnswerResponse;
import com.example.demo.service.AnswerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService service;

    public AnswerController(AnswerService service) {
        this.service = service;
    }

    // Vastauksen haku ID:llä
    @GetMapping("/{id}")
    public AnswerResponse getAnswer(@PathVariable Long id) {
        return service.getAnswerById(id);
    }

    // Vastauksen muokkaus
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody AnswerRequest answerDetails) {
        service.updateAnswer(id, answerDetails);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/my")
    public List<AnswerResponse> getAnswersByUser() {
        return service.getAnswersByUser();
    }
}
