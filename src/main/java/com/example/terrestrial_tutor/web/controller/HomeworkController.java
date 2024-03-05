package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.annotations.Api;
import com.example.terrestrial_tutor.dto.PupilDTO;
import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.payload.request.HomeworkAddRequest;
import com.example.terrestrial_tutor.service.HomeworkService;
import com.example.terrestrial_tutor.service.impl.HomeworkServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Api
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;
    @PostMapping("/homework/add")
    public ResponseEntity<HomeworkEntity> addHomework(Principal principal, @RequestBody HomeworkAddRequest request) {
        HomeworkEntity newHomework = homeworkService.addHomework(request);
        return new ResponseEntity<>(newHomework, HttpStatus.OK);
    }
}
