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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Api
public class SupportController {

    @Autowired
    TaskService taskService;

    @Autowired
    UploadFilesService uploadFilesService;

    @PostMapping("/support/add/task")
    public HttpStatus addTask(@RequestParam(required = false) MultipartFile files,
                              @RequestParam String name,
                              @RequestParam int checking,
                              @RequestParam String answerType,
                              @RequestParam String taskText,
                              @RequestParam String answer,
                              @RequestParam String subject,
                              @RequestParam String level1,
                              @RequestParam String level2,
                              @RequestParam List<String> homeworks
                              ) {
        try{
            TaskDTO taskDTO = new TaskDTO(name, checking, answerType, taskText, answer, subject, level1, level2, "", homeworks);
            if (files != null) {
                taskDTO.setFile(uploadFilesService.uploadFiles(files));
            }
            SupportEntity support = (SupportEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            TaskEntity task = taskService.addNewTask(taskDTO, support);
        }
        catch (Exception e){
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }
        return HttpStatus.OK;
    }
}
