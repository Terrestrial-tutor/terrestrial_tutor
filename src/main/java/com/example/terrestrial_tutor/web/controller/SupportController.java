package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.annotations.Api;
import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.service.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
@Api
public class SupportController {

    @Autowired
    TaskServiceImpl taskService;

    @PostMapping("/support/add/task")
    public HttpStatus addTask(@RequestBody TaskDTO dto) {
        try{
            taskService.addNewTask(dto);
        }
        catch (Exception e){
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }
        return HttpStatus.OK;
    }
}
