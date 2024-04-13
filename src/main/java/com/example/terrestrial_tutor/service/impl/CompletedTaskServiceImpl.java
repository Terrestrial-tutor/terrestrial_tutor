package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.CompletedTaskEntity;
import com.example.terrestrial_tutor.repository.CompletedTaskRepository;
import com.example.terrestrial_tutor.service.CompletedTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompletedTaskServiceImpl implements CompletedTaskService {

    @Autowired
    CompletedTaskRepository completedTaskRepository;

    @Override
    public CompletedTaskEntity save(CompletedTaskEntity completedTaskEntity) {
        return completedTaskRepository.save(completedTaskEntity);
    }
}
