package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.AttemptEntity;
import com.example.terrestrial_tutor.repository.AttemptRepository;
import com.example.terrestrial_tutor.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AttemptRepository attemptRepository;

    @Override
    public AttemptEntity addNewAnswer(AttemptEntity entity) {
        return attemptRepository.save(entity);
    }
}
