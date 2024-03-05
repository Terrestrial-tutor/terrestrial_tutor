package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.CheckDTO;
import com.example.terrestrial_tutor.dto.TutorListDTO;
import com.example.terrestrial_tutor.entity.CheckEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import com.example.terrestrial_tutor.service.PupilService;
import com.example.terrestrial_tutor.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class TutorListFacade {

    public List<TutorListDTO> tutorListToDTO(List<TutorEntity> tutors) {
        return tutors
                .stream()
                .map(tutorEntity -> {
                    TutorListDTO tutorDTO = new TutorListDTO();
                    tutorDTO.setId(tutorEntity.getId());
                    tutorDTO.setName(tutorEntity.getName());
                    tutorDTO.setSurname(tutorEntity.getSurname());
                    tutorDTO.setPatronymic(tutorEntity.getPatronymic());
                    tutorDTO.setUsername(tutorEntity.getUsername());
                    return tutorDTO;
                })
                .toList();
    }
}
