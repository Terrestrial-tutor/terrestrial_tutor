package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.*;
import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.entity.enums.TaskCheckingType;
import com.example.terrestrial_tutor.repository.HomeworkRepository;
import com.example.terrestrial_tutor.repository.PupilRepository;
import com.example.terrestrial_tutor.repository.TaskRepository;
import com.example.terrestrial_tutor.service.HomeworkService;
import com.example.terrestrial_tutor.service.SubjectService;
import com.example.terrestrial_tutor.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    SubjectService subjectService;

    public HomeworkEntity addHomework(HomeworkDTO request) {
        // todo получить авторизированного пользователя и установить в tutor
        TutorEntity tutor = (TutorEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HomeworkEntity newHomework = new HomeworkEntity();
        newHomework.setName(request.getName());
        newHomework.setTargetTime(request.getTargetTime());
        newHomework.setPupils(request.getPupilIds().stream()
                .map(id -> pupilRepository.getById(id))
                .collect(Collectors.toList()));
        newHomework.setDeadLine(request.getDeadLine());
        newHomework.setTutor(tutor);
        if (request.getTasksCheckingTypes() != null) {
            Map<TaskEntity, TaskCheckingType> newMap = new HashMap<>();
            request.getTasksCheckingTypes().forEach((key, value) ->
                    newMap.put(taskRepository.getById(key), taskService.toType(value)));
            newHomework.setTasksCheckingTypes(newMap);
        }
        SubjectEntity subject = subjectService.findSubjectByName(request.getSubject());
        newHomework.setSubject(subject);
        List<HomeworkEntity> subjectHomeworks = subject.getHomeworkList();
        subjectHomeworks.add(newHomework);
        subject.setHomeworkList(subjectHomeworks);
        subjectService.updateSubject(subject);
        return homeworkRepository.save(newHomework);
    }
    public HomeworkEntity getHomeworkById(Long id){
        return homeworkRepository.getById(id);
    }

    public void deleteHomeworkById(Long id) {
        homeworkRepository.deleteById(id);
    }
//    public List<HomeworkEntity> getAllHomeworksPupil(){
//        PupilEntity pupil = (PupilEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return homeworkRepository.findHomeworkEntitiesByPupils(new PupilEntity[]{pupil});
//    }

    public List<HomeworkEntity> getAllHomeworksTutor(){
        TutorEntity tutor = (TutorEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return homeworkRepository.findHomeworkEntitiesByTutor(tutor);
    }

    public boolean isHomeworkEmpty(Long id){
        HomeworkEntity homework = getHomeworkById(id);
        return homework.getTasks().isEmpty() && homework.getName() == null;
    }
}
