package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.annotations.Api;
import com.example.terrestrial_tutor.dto.SelectionDTO;
import com.example.terrestrial_tutor.dto.facade.HomeworkFacade;
import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.service.HomeworkService;
import com.example.terrestrial_tutor.service.SubjectService;
import com.example.terrestrial_tutor.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Api
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;
    @Autowired
    TaskService taskService;
    @Autowired
    HomeworkFacade homeworkFacade;
    @Autowired
    SubjectService subjectService;

    //todo контроллер для обработки завершенной домашки

    @PostMapping("/homework/add")
    public ResponseEntity<Long> addHomework(@RequestBody String subject) {
        HomeworkEntity newHomework = homeworkService.addHomework(new HomeworkDTO(subject));
        return new ResponseEntity<>(newHomework.getId(), HttpStatus.OK);
    }

    /**
     * Контроллер для отдачи случайной выборки заданий по заданным данным и формированием в дз
     * @param selectionDTO - входные данные, см. "SelectionDTO"
     * @return лист выборки
     */
    @PostMapping("/homework/selection")
    public ResponseEntity<List<TaskEntity>> getTasksSelection(@RequestBody SelectionDTO selectionDTO) {
        SubjectEntity currentSubject = subjectService.findSubjectByName(selectionDTO.getSubject());
        List<TaskEntity> result = taskService.getSelectionTask(selectionDTO.getChoices(), currentSubject);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/homework/{id}")
    public ResponseEntity<HomeworkDTO> getHomeworkById(@PathVariable Long id) {
        HomeworkEntity homework = homeworkService.getHomeworkById(id);
        return new ResponseEntity<>(homeworkFacade.homeworkToHomeworkDTO(homework), HttpStatus.OK);
    }

    @DeleteMapping("/homework/delete/{id}")
    public HttpStatus deleteHomeworkById(@PathVariable Long id) {
        homeworkService.deleteHomeworkById(id);
        return HttpStatus.OK;
    }

    @GetMapping("/homework/empty/{id}")
    public ResponseEntity<Boolean> isHomeworkEmpty(@PathVariable Long id) {
        return new ResponseEntity<>(homeworkService.isHomeworkEmpty(id), HttpStatus.OK);
    }
}
