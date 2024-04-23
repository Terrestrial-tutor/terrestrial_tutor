package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.CheckDTO;
import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.dto.PupilDTO;
import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.service.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PupilFacade {

    @Autowired
    PupilService pupilService;
    @Autowired
    HomeworkFacade homeworkFacade;

    public PupilDTO pupilToPupilDTO(PupilEntity pupil) {
        PupilDTO pupilDTO = new PupilDTO();
        pupilDTO.setId(pupil.getId());
        pupilDTO.setBalance(pupil.getBalance());
        pupilDTO.setHomeworks((pupil.getHomeworkList()
                .stream()
                .map(homework -> {
                    HomeworkDTO clearedHomework = homeworkFacade.homeworkToHomeworkDTO(homework);
                    List<TaskDTO> clearedTasks = new ArrayList<>();
                    clearedHomework.getTasks().forEach(taskDTO -> {
                        clearedTasks.add(new TaskDTO(taskDTO.getName(), taskDTO.getChecking(), taskDTO.getAnswerType(),
                                taskDTO.getTaskText(), null, taskDTO.getSubject(), taskDTO.getLevel1(),
                                taskDTO.getLevel2(), taskDTO.getTable()));
                        clearedTasks.get(clearedTasks.size() - 1).setFiles(taskDTO.getFiles());

                    });
                    clearedHomework.setTasks(new HashSet<>(clearedTasks));
                    return clearedHomework;
                })
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
        pupilDTO.setName(pupil.getName());
        pupilDTO.setSurname(pupil.getSurname());
        pupilDTO.setPatronymic(pupil.getPatronymic());
        return pupilDTO;
    }
}
