package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.dto.PupilDTO;
import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.service.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
                    List<TaskDTO> tasks = new ArrayList<>();
                    clearedHomework.getTasks().forEach(taskDTO -> {
                        if (taskDTO.getAnswerType().equals("Варианты")) {
                            Collections.shuffle(taskDTO.getAnswers());
                            tasks.add(taskDTO);
                        } else {
                            TaskDTO clearedTask = new TaskDTO(taskDTO.getName(), taskDTO.getChecking(),
                                    taskDTO.getAnswerType(), taskDTO.getTaskText(), null, taskDTO.getSubject(),
                                    taskDTO.getLevel1(), taskDTO.getLevel2(), taskDTO.getTable());
                            clearedTask.setFiles(taskDTO.getFiles());
                            clearedTask.setId(taskDTO.getId());
                            tasks.add(clearedTask);
                        }
                    });
                    clearedHomework.setTasks(new LinkedList<>(tasks));
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
                .map(tutorEntity -> tutorEntity.getSurname() + " " + tutorEntity.getName() + " " + tutorEntity.getPatronymic())
                .toList()));
        pupilDTO.setUsername(pupil.getUsername());
        pupilDTO.setName(pupil.getName());
        pupilDTO.setSurname(pupil.getSurname());
        pupilDTO.setPatronymic(pupil.getPatronymic());
        return pupilDTO;
    }
}
