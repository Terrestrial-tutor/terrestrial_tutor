package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.annotations.Api;
import com.example.terrestrial_tutor.dto.CheckDTO;
import com.example.terrestrial_tutor.dto.facade.CheckFacade;
import com.example.terrestrial_tutor.entity.CheckEntity;
import com.example.terrestrial_tutor.entity.enums.ERole;
import com.example.terrestrial_tutor.service.AdminService;
import com.example.terrestrial_tutor.service.CheckService;
import com.example.terrestrial_tutor.service.PupilService;
import com.example.terrestrial_tutor.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api
public class ChecksController {

    @Autowired
    PupilService pupilService;
    @Autowired
    TutorService tutorService;
    @Autowired
    CheckService checkService;
    @Autowired
    AdminService adminService;
    @Autowired
    private CheckFacade checkFacade;

    @GetMapping("/check/all")
    public ResponseEntity<List<CheckDTO>> checks() {
        List<CheckDTO> checkList =checkService.getAllChecks()
                .stream()
                .map(checkFacade::checkToCheckDTO)
                .toList();
        return new ResponseEntity<>(checkList, HttpStatus.OK);
    }

    @DeleteMapping("/check/close/{id}")
    public ResponseEntity<CheckEntity> closeCheck(@PathVariable Long id) {
        CheckEntity check = checkService.findCheckById(id);
        if (check.getRole() == ERole.PUPIL) {
            pupilService.verifyPupil(check.getCandidateId());
        } else {
            tutorService.verifyTutor(check.getCandidateId());
        }
        return new ResponseEntity<>(checkService.deleteCheck(id), HttpStatus.OK);
    }

}
