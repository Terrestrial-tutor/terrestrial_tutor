package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.annotations.Api;
import com.example.terrestrial_tutor.dto.PupilDTO;
import com.example.terrestrial_tutor.dto.facade.PupilFacade;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.service.PupilService;
import com.example.terrestrial_tutor.service.SubjectService;
import com.example.terrestrial_tutor.service.TutorService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Api
public class TutorController {

    @Autowired
    @NonNull
    PupilService pupilService;
    @Autowired
    @NonNull
    TutorService tutorService;
    @Autowired
    private PupilFacade pupilFacade;

    @GetMapping("/tutor/find/pupils/{subject}/{id}")
    public ResponseEntity<List<PupilDTO>> getTutorPupilsBySubject(@PathVariable String subject, @PathVariable Long id) {
        List<PupilEntity> pupils = tutorService.findTutorById(id).getPupils();
        List<PupilDTO> pupilsDTO = new ArrayList<>();
//                pupils
//                .stream()
//                .map(pupil -> {
//                    for (SubjectEntity pupilSubject : pupil.getSubjects()) {
//                        if (pupilSubject.getName().equals(subject)) {
//                            return pupilFacade.pupilToPupilDTO(pupil);
//                        }
//                    }
//                    return null;
//                })
//                .toList();
        for (PupilEntity pupil : pupils) {
            for (SubjectEntity pupilSubject : pupil.getSubjects()) {
                if (pupilSubject.getName().equals(subject)) {
                    pupilsDTO.add(pupilFacade.pupilToPupilDTO(pupil));
                }
            }
        }
        return new ResponseEntity<>(pupilsDTO, HttpStatus.OK);
    }

}
