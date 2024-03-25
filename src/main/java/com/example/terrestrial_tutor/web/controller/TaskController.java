package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.annotations.Api;
import com.example.terrestrial_tutor.dto.SelectionDTO;
import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.dto.facade.TaskFacade;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.service.SubjectService;
import com.example.terrestrial_tutor.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Api
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    TaskFacade taskFacade;

    @GetMapping("/tasks/all")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskEntity> tasksList = taskService.getAllTasks();
        List<TaskDTO> tasksDTO = new ArrayList<>();
        for (TaskEntity task : tasksList) {
            tasksDTO.add(taskFacade.taskToTaskDTO(task));
        }
        return new ResponseEntity<>(tasksDTO, HttpStatus.OK);
    }

    /**
     * Контроллер для отдачи выборки заданий по предмету и верхней заданной теме
     * @param subject - предмет
     * @param level1 - верхняя тема выборки заданий
     * @return лист выборки заданий
     */
    @GetMapping("/tasks/{subject}/{level1}")
    public ResponseEntity<List<TaskEntity>> getTasksByLevel1(@PathVariable String subject, @PathVariable String level1) {
        SubjectEntity currentSubject = subjectService.findSubjectByName(subject);
        List<TaskEntity> listTasks = taskService.getTasksBySubjectAndLevel1(currentSubject, level1);
        return new ResponseEntity<>(listTasks, HttpStatus.OK);
    }

    @GetMapping("/tasks/{subject}")
    public ResponseEntity<List<TaskDTO>> getTasksBySubject(@PathVariable String subject) {
        SubjectEntity currentSubject = subjectService.findSubjectByName(subject);
        List<TaskEntity> tasksList = taskService.getTasksBySubject(currentSubject);
        List<TaskDTO> tasksDTO = new ArrayList<>();
        for (TaskEntity task : tasksList) {
            tasksDTO.add(taskFacade.taskToTaskDTO(task));
        }
        return new ResponseEntity<>(tasksDTO, HttpStatus.OK);
    }

    /**
     * Контроллер для отдачи выборки заданий по предмету, верхней теме и подтеме
     * @param subject - предмет
     * @param level1 - верхняя тема выборки задания
     * @param level2 - подтема выборки задания
     * @return лист выборки задания
     */
    @GetMapping("/tasks/{subject}/{level1}/{level2}")
    public ResponseEntity<List<TaskEntity>> getTasksByLevel1AndLevel2(@PathVariable String subject, @PathVariable String level1,
                                                                      @PathVariable String level2) {
        SubjectEntity currentSubject = subjectService.findSubjectByName(subject);
        List<TaskEntity> listTasks = taskService.getTasksBySubjectAndLevel2(currentSubject, level1, level2);
        return new ResponseEntity<>(listTasks, HttpStatus.OK);
    }

    /**
     * Контроллер для отдачи случайной выборки заданий по заданным данным
     * @param selectionDTO - входные данные, см. "SelectionDTO"
     * @return лист выборки
     */
    @PostMapping("/tasks/selection")
    public ResponseEntity<List<TaskEntity>> getTasksByLevel1AndLevel2(@RequestBody SelectionDTO selectionDTO) {
        SubjectEntity currentSubject = subjectService.findSubjectByName(selectionDTO.getSubject());
        List<TaskEntity> result = taskService.getSelectionTask(selectionDTO.getChoices(), currentSubject);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
