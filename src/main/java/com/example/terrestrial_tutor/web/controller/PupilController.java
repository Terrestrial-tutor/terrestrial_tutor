package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.annotations.Api;
import com.example.terrestrial_tutor.dto.PupilDTO;
import com.example.terrestrial_tutor.dto.facade.PupilFacade;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.service.PupilService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Api
public class PupilController {

    @Autowired
    @NonNull
    PupilService pupilService;
    @Autowired
    private PupilFacade pupilFacade;

    /*@PostMapping("/pupil/add")
    public ResponseEntity<PupilEntity> addPupil(@RequestBody PupilEntity pupil) {
        return new ResponseEntity<>(pupilService.addNewPupil(pupil), HttpStatus.OK);
    }*/

    @GetMapping("/pupil/find")
    public ResponseEntity<PupilEntity> findPupilById(@RequestHeader Long id) {
        return new ResponseEntity<>(pupilService.findPupilById(id), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost")
    @GetMapping("/pupil")
    public ResponseEntity<PupilDTO> getCurrentPupil(Principal principal) {
        PupilDTO pupil = pupilFacade.pupilToPupilDTO(pupilService.getCurrentPupil(principal));
        return new ResponseEntity<PupilDTO>(pupil, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost")
    @PostMapping("/pupil/add/subjects")
    public ResponseEntity<PupilDTO> addSubjects(Principal principal, @RequestBody List<String> subjects) {
        PupilEntity pupil = pupilService.getCurrentPupil(principal);
        List<SubjectEntity> subjectEntity = (List<SubjectEntity>) subjects
                .stream()
                .map(subject -> {
                    SubjectEntity subjectEntity1 = new SubjectEntity();
                    subjectEntity1.setName(subject);
                    subjectEntity1.setPupils((List<PupilEntity>) pupil);
                    return subjectEntity1;
                });
        PupilDTO pupilDTO = pupilFacade.pupilToPupilDTO(pupilService.addSubjects(principal, subjectEntity));
        return new ResponseEntity<>(pupilDTO,HttpStatus.OK);
    }

}
