package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.AnswerEntity;
import com.example.terrestrial_tutor.repository.AnswerRepository;
import com.example.terrestrial_tutor.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public AnswerEntity addNewAnswer(AnswerEntity entity) {
        return answerRepository.save(entity);
    }
}
