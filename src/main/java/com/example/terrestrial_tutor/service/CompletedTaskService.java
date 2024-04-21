package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.CompletedTaskEntity;

public interface CompletedTaskService {
    CompletedTaskEntity save(CompletedTaskEntity completedTaskEntity);

    CompletedTaskEntity getByTask(Long id);
}
