package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.SupportEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.payload.request.AddTaskRequest;

import java.util.List;
import java.util.Map;

public interface TaskService {
    List<TaskEntity> getTasksBySubjectAndLevel1(SubjectEntity subject, String level1);

    List<TaskEntity> getTasksBySubjectAndLevel2(SubjectEntity subject, String level1, String level2);

    List<TaskEntity> getAllTasks();

    List<TaskEntity> getSelectionTask(Map<String, Integer> choices, SubjectEntity subject);

    TaskEntity addNewTask(AddTaskRequest dto, SupportEntity support);
}
