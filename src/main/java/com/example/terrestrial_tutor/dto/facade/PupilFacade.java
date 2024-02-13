package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.CheckDTO;
import com.example.terrestrial_tutor.dto.PupilDTO;
import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.service.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PupilFacade {

    @Autowired
    PupilService pupilService;

    public PupilDTO pupilToPupilDTO(PupilEntity pupil) {
        PupilDTO pupilDTO = new PupilDTO();
        pupilDTO.setId(pupil.getId());
        pupilDTO.setBalance(pupil.getBalance());
        pupilDTO.setHomeworks((pupil.getHomeworkList()
                .stream()
                .map(HomeworkEntity::getName)
                .toList()));
        pupilDTO.setPrice(pupil.getPrice());
        pupilDTO.setSubjects((pupil.getSubjects()
                .stream()
                .map(SubjectEntity::getName)
                .toList()));
        pupilDTO.setTutors((pupil.getTutors()
                .stream()
                .map(tutorEntity -> {
                    return tutorEntity.getSurname() + " " + tutorEntity.getName() + " " + tutorEntity.getPatronymic();
                })
                .toList()));
        pupilDTO.setUsername(pupil.getUsername());
        return pupilDTO;
    }
}
