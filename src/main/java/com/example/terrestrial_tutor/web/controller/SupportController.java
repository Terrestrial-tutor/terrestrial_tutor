package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.annotations.Api;
import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.entity.SupportEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.service.TaskService;
import com.example.terrestrial_tutor.service.UploadFilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * Контроллер для работы с тех поддержкой
 */
@RequiredArgsConstructor
@Controller
@Api
public class SupportController {

    @Autowired
    TaskService taskService;

    @Autowired
    UploadFilesService uploadFilesService;

    /**
     * Добавление задания в бд
     *
     * @param files      файлы
     * @param name       название
     * @param checking   тип проверки
     * @param answerType тип ответа
     * @param taskText   текст задания
     * @param answer     ответы
     * @param subject    предмет
     * @param level1     тема верхнего уровня
     * @param level2     тема нижнего уровня
     * @param table      таблица к заданию
     * @return статус операции
     * @throws Exception
     */
    @PostMapping("/support/add/task")
    public ResponseEntity<Long> addTask(@RequestBody TaskDTO task) {
        System.out.println(task.getName());
        SupportEntity support = (SupportEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long taskId = taskService.addNewTask(task, support).getId();
        return new ResponseEntity<Long>(taskId, HttpStatus.OK);
    }

    @PostMapping("/support/add/file/{id}")
    public HttpStatus addFileыForTask(@PathVariable Long id, 
                                                @RequestParam(required = false) Set<MultipartFile> files
    ) throws Exception {
        System.out.println(id);
        try {
            TaskEntity task = taskService.getTaskById(id);
            if (files != null) {
                task.setFiles(uploadFilesService.uploadFiles(files, task));
            }
            System.out.println("Ok");
            taskService.save(task);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return HttpStatus.OK;
    }
}
