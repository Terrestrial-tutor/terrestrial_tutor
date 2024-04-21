package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.*;
import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.entity.enums.TaskCheckingType;
import com.example.terrestrial_tutor.repository.CompletedTaskRepository;
import com.example.terrestrial_tutor.repository.HomeworkRepository;
import com.example.terrestrial_tutor.repository.PupilRepository;
import com.example.terrestrial_tutor.repository.TaskRepository;
import com.example.terrestrial_tutor.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    HomeworkRepository homeworkRepository;

    @Autowired
    PupilRepository pupilRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    PupilService pupilService;

    @Autowired
    TutorService tutorService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    CompletedTaskServiceImpl completedTaskService;

    public HomeworkEntity saveHomework(HomeworkEntity homework) {
        if (!homework.getTutor().getHomeworkList().contains(homework)) {
            Set<HomeworkEntity> currentTutorHomeworks = homework.getTutor().getHomeworkList();
            currentTutorHomeworks.add(homework);
            homework.getTutor().setHomeworkList(currentTutorHomeworks);
        }
        HomeworkEntity finalHomework = homework;
        homework.setCompletedTaskEntities(homework.getCompletedTaskEntities().stream().peek(task -> {
            task.setHomework(finalHomework);
        }).collect(Collectors.toSet()));
        homework = homeworkRepository.save(homework);
        tutorService.updateTutor(homework.getTutor());
        return homework;
    }
    public HomeworkEntity getHomeworkById(Long id){
        return homeworkRepository.findHomeworkEntityById(id);
    }

    public void deleteHomeworkById(Long id) {
        HomeworkEntity homework = getHomeworkById(id);
        TutorEntity tutor = homework.getTutor();
        tutor.getHomeworkList().remove(homework);
        tutorService.updateTutor(tutor);
//        homework.setTutor(null);
        homeworkRepository.delete(homeworkRepository.findHomeworkEntityById(id));
    }

    public HomeworkEntity save(HomeworkEntity homework) {

        return homeworkRepository.save(homework);
    }
//    public List<HomeworkEntity> getAllHomeworksPupil(){
//        PupilEntity pupil = (PupilEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return homeworkRepository.findHomeworkEntitiesByPupils(new PupilEntity[]{pupil});
//    }

    public List<HomeworkEntity> getAllHomeworksTutor(){
        TutorEntity tutor = (TutorEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return homeworkRepository.findHomeworkEntitiesByTutor(tutor);
    }
}
