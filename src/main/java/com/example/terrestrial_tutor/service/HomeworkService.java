package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import com.example.terrestrial_tutor.dto.HomeworkDTO;

import java.util.List;

public interface HomeworkService {

    HomeworkEntity addHomework(HomeworkDTO request);

    List<HomeworkEntity> getAllHomeworksPupil();
    List<HomeworkEntity> getAllHomeworksTutor();
    HomeworkEntity getHomeworkById(Long id);

}
