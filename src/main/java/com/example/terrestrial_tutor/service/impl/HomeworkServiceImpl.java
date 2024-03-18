package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import com.example.terrestrial_tutor.payload.request.HomeworkAddRequest;
import com.example.terrestrial_tutor.repository.HomeworkRepository;
import com.example.terrestrial_tutor.repository.PupilRepository;
import com.example.terrestrial_tutor.repository.TaskRepository;
import com.example.terrestrial_tutor.service.HomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
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

    public List<HomeworkEntity> getHomeworksByPupil(PupilEntity pupil) {
        return homeworkRepository.findHomeworkEntitiesByPupil(pupil);
    }

    public List<HomeworkEntity> getHomeworksByTutor(TutorEntity tutor) {
        return homeworkRepository.findHomeworkEntitiesByTutor(tutor);
    }

    public HomeworkEntity addHomework(HomeworkAddRequest request) {
        // todo получить авторизированного пользователя и установить в tutor
        HomeworkEntity newHomework = new HomeworkEntity();
        newHomework.setName(request.getName());
        newHomework.setSoluteTime(request.getSoluteTime());
        newHomework.setPupil(pupilRepository.getById(request.getPupilId()));
        //newHomework.setTutor(request.getTutor());
        newHomework.setTasks(request.getTasksId().stream()
                .map(id -> taskRepository.getById(id))
                .collect(Collectors.toList()));
        return homeworkRepository.save(newHomework);
    }

}
