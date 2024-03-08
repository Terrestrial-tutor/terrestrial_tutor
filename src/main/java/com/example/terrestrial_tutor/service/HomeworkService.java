package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import com.example.terrestrial_tutor.payload.request.HomeworkAddRequest;

import java.util.List;

public interface HomeworkService {
    List<HomeworkEntity> getHomeworksByPupil(PupilEntity pupil);

    List<HomeworkEntity> getHomeworksByTutor(TutorEntity tutor);

    HomeworkEntity addHomework(HomeworkAddRequest request);

}
