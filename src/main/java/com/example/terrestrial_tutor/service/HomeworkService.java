package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.dto.HomeworkDTO;

import java.util.List;

public interface HomeworkService {

    HomeworkEntity addHomework(HomeworkDTO request);

//    List<HomeworkEntity> getAllHomeworksPupil();
    List<HomeworkEntity> getAllHomeworksTutor();
    HomeworkEntity getHomeworkById(Long id);
    void deleteHomeworkById(Long id);
    HomeworkEntity save(HomeworkEntity homework);
}
