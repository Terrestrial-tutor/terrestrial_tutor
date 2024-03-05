package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    List<TaskEntity> getTasksBySubjectAndLevel1(SubjectEntity subject, String level1);

    List<TaskEntity> getTasksBySubjectAndLevel2(SubjectEntity subject, String level2);
}
