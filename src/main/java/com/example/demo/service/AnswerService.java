package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Answer;
import com.example.demo.repository.AnswerRepository;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository repository) {
        this.answerRepository = repository;
    }

    // Vastauksen haku ID:llä
    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    // Vastauksen muokkaus
    public Answer updateAnswer(Long id, Answer answerDetails) {
        Answer answer = answerRepository.findById(id).orElseThrow();
        answer.setContent(answerDetails.getContent());
        return answerRepository.save(answer);
    }

    // Hae omat vastaukset
    public List<Answer> getAnswersByUser(Long userId) {
        return answerRepository.findByUserId(userId);
    }
}
