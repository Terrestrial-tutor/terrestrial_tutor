package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.TutorListDTO;
import com.example.terrestrial_tutor.entity.TutorEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Класс для перевода сущностей репетиторов в DTO
 */

@Component
public class TutorListFacade {

    /**
     * Метод для перевода сущностей репетиторов в DTO
     *
     * @param tutors репетиторы
     * @return DTO из списка репетиторов
     */
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
