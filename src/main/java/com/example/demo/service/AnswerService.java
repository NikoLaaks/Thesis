package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AnswerResponse;
import com.example.demo.entity.Answer;
import com.example.demo.mapper.AnswerMapper;
import com.example.demo.repository.AnswerRepository;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository repository) {
        this.answerRepository = repository;
    }

    // Vastauksen haku ID:llä
    public AnswerResponse getAnswerById(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow();
        return AnswerMapper.mapToResponse(answer);
    }

    // Vastauksen muokkaus
    public Answer updateAnswer(Long id, Answer answerDetails) {
        Answer answer = answerRepository.findById(id).orElseThrow();
        answer.setContent(answerDetails.getContent());
        return answerRepository.save(answer);
    }

    // Hae omat vastaukset
    public List<AnswerResponse> getAnswersByUser(Long userId) {
        // Jokainen entity muunnetaan erikseen responseksi
        return answerRepository.findByUserId(userId)
                .stream()
                .map(AnswerMapper::mapToResponse)
                .toList();
    }

}
