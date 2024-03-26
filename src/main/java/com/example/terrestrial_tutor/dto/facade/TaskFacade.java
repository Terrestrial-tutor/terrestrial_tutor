package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                task.getAnswer(),
                task.getSubject().getName(),
                task.getLevel1(),
                task.getLevel2());
        taskDTO.setFiles(task.getFiles());
        return taskDTO;
    }
}
