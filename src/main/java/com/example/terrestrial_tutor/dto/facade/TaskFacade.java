package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.service.TaskService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class TaskFacade {

    @Autowired
    TaskService taskService;

    public TaskDTO taskToTaskDTO(TaskEntity task) {
        TaskDTO taskDTO = new TaskDTO(
                task.getName(),
                task.getChecking(),
                task.getAnswerType(),
                task.getTaskText(),
                new Gson().fromJson(task.getAnswer(), LinkedList.class),
                task.getSubject().getName(),
                task.getLevel1(),
                task.getLevel2(),
                task.getTable());
        taskDTO.setFiles(task.getFiles());
        taskDTO.setId(task.getId());
        return taskDTO;
    }
}
