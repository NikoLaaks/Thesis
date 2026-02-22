package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AnswerRequest;
import com.example.demo.dto.AnswerResponse;
import com.example.demo.entity.Answer;
import com.example.demo.entity.User;
import com.example.demo.mapper.AnswerMapper;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    // Vastauksen haku ID:llä
    public AnswerResponse getAnswerById(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow();
        return AnswerMapper.mapToResponse(answer);
    }

    // Vastauksen muokkaus
    public Answer updateAnswer(Long id, AnswerRequest answerDetails) {
        Answer answer = answerRepository.findById(id).orElseThrow();
        answer.setContent(answerDetails.getContent());
        return answerRepository.save(answer);
    }

    // Hae omat vastaukset
    public List<AnswerResponse> getAnswersByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        // Jokainen entity muunnetaan erikseen responseksi
        return answerRepository.findByUserId(user.getId())
                .stream()
                .map(AnswerMapper::mapToResponse)
                .toList();
    }

}
