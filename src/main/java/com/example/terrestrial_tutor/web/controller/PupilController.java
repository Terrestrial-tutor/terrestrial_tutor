package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.repository.PupilRepository;
import com.example.terrestrial_tutor.service.PupilService;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RequiredArgsConstructor
@Controller
public class PupilController {

    @Autowired
    @NonNull
    PupilService pupilService;
    @Autowired
    @NonNull
    PupilRepository pupilRepository;

    @PostMapping("/pupil/add")
    public ResponseEntity<PupilEntity> addPupil(@RequestBody PupilEntity pupil) {
        return new ResponseEntity<>(pupilService.addNewPupil(pupil), HttpStatus.OK);
    }

    @GetMapping("/pupil/find")
    public ResponseEntity<PupilEntity> findPupilById(@RequestHeader Long id) {
        return new ResponseEntity<>(pupilRepository.findPupilEntityById(id), HttpStatus.OK);
    }

}
