package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.annotations.Api;
import com.example.terrestrial_tutor.dto.PupilDTO;
import com.example.terrestrial_tutor.dto.TutorListDTO;
import com.example.terrestrial_tutor.dto.facade.PupilFacade;
import com.example.terrestrial_tutor.dto.facade.TutorListFacade;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import com.example.terrestrial_tutor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Api
public class AdminController {

    @Autowired
    PupilService pupilService;
    @Autowired
    TutorService tutorService;
    @Autowired
    AdminService adminService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    TutorListFacade tutorListFacade;
    @Autowired
    private PupilFacade pupilFacade;

    @GetMapping("/admin/subject/{subject}/find/tutors")
    public ResponseEntity<List<TutorListDTO>> findTutorsBySubject(@PathVariable String subject) {
        return new ResponseEntity<>(tutorListFacade.tutorListToDTO(subjectService.findSubjectTutors(subject)), HttpStatus.OK);
    }

    @PostMapping("/admin/tutor/{id}/add/pupils")
    public ResponseEntity<List<PupilDTO>> addPupilsForTutor(@RequestBody List<Long> pupilsIds, @PathVariable Long id) {
        List<PupilEntity> pupils = pupilService.findPupilsByIds(pupilsIds);
        TutorEntity tutor = tutorService.findTutorById(id);
        for (PupilEntity pupil : pupils) {
            pupil.getTutors().add(tutor);
            pupilService.updatePupil(pupil);
        }
        tutor.getPupils().addAll(pupils);
        tutorService.updateTutor(tutor);
        List<PupilDTO> pupilsDTO = tutor.getPupils()
                .stream()
                .map(pupil -> pupilFacade.pupilToPupilDTO(pupil))
                .toList();
        return new ResponseEntity<>(pupilsDTO, HttpStatus.OK);
    }

    @GetMapping("/admin/find/pupils/new/{subject}")
    public ResponseEntity<List<PupilDTO>> findPupilsWithoutSubject(@PathVariable String subject) {
        List<PupilEntity> allPupils = pupilService.findAllPupils();
        List<PupilDTO> resultPupils = new ArrayList<>();
        for (PupilEntity pupil : allPupils) {
            List<String> pupilSubjects = pupil.getSubjects()
                    .stream()
                    .map(SubjectEntity::getName)
                    .toList();
            if (!pupilSubjects.contains(subject)) {
                resultPupils.add(pupilFacade.pupilToPupilDTO(pupil));
            }
        }
        return new ResponseEntity<>(resultPupils, HttpStatus.OK);
    }

}
