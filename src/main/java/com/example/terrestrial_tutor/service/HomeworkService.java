package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.dto.HomeworkAnswerDTO;
import com.example.terrestrial_tutor.entity.HomeworkEntity;

import java.util.List;
import java.util.Map;

public interface HomeworkService {

    HomeworkEntity saveHomework(HomeworkEntity homework);

//    List<HomeworkEntity> getAllHomeworksPupil();
    List<HomeworkEntity> getAllHomeworksTutor();
    HomeworkEntity getHomeworkById(Long id);
    void deleteHomeworkById(Long id);
    HomeworkEntity save(HomeworkEntity homework);
    HomeworkAnswerDTO checkingAnswers(Map<Long, String> answers, Long idHomework);
}
