package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Answer;
import com.example.demo.repository.AnswerRepository;

@Component("answerSecurity")
public class AnswerSecurity {
    private final AnswerRepository answerRepository;

    public AnswerSecurity(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public boolean isOwner(Long answerId, Authentication authentication) {

        Answer answer = answerRepository.findById(answerId).orElseThrow();

        String username = authentication.getName();

        return answer.getUser().getUserName().equals(username);
    }
}
