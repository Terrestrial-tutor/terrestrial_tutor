package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskFacade {

    @Autowired
    TaskService taskService;

    public TaskDTO taskToTaskDTO(TaskEntity task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setTaskText(task.getTaskText());
        taskDTO.setAnswer(task.getAnswer());
        task.setSubject(task.getSubject());
        taskDTO.setLevel1(task.getLevel1());
        taskDTO.setLevel2(task.getLevel2());
        taskDTO.setHomeworks(task.getHomeworks()
                .stream()
                .map(HomeworkEntity::getName)
                .toList());
        return taskDTO;
    }
}
