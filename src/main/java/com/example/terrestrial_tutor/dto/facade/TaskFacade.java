package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.exceptions.CustomException;
import com.example.terrestrial_tutor.service.TaskService;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * Класс для перевода сущности задания в сущность DTO
 */
@Component
public class TaskFacade {

    @Autowired
    TaskService taskService;

    /**
     * Метод для перевода сущности задания в сущность DTO
     *
     * @param task задание
     * @return задание DTO
     */

    public TaskDTO taskToTaskDTO(TaskEntity task) {
        try {
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
        } catch (JsonParseException e) {
            throw new CustomException("Error parsing JSON");
        } catch (Exception e) {
            throw new CustomException("Error converting task to taskDTO");
        }
    }
}
