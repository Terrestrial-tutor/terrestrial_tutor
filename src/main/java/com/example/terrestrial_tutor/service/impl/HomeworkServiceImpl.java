package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import com.example.terrestrial_tutor.payload.request.HomeworkAddRequest;
import com.example.terrestrial_tutor.repository.HomeworkRepository;
import com.example.terrestrial_tutor.service.HomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    HomeworkRepository homeworkRepository;

    public List<HomeworkEntity> getHomeworksByPupil(PupilEntity pupil) {
        return homeworkRepository.findHomeworkEntitiesByPupil(pupil);
    }

    public List<HomeworkEntity> getHomeworksByTutor(TutorEntity tutor) {
        return homeworkRepository.findHomeworkEntitiesByTutor(tutor);
    }

    public HomeworkEntity addHomework(HomeworkAddRequest request) {
        HomeworkEntity newHomework = new HomeworkEntity();
        newHomework.setName(request.getName());
        newHomework.setSoluteTime(request.getSoluteTime());
        newHomework.setPupil(request.getPupil());
        newHomework.setTutor(request.getTutor());
        newHomework.setTasks(request.getTasks());
        return homeworkRepository.save(newHomework);
    }
}
