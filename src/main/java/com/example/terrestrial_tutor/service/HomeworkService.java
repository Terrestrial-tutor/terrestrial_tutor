package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.dto.HomeworkAnswersDTO;
import com.example.terrestrial_tutor.entity.AnswerEntity;
import com.example.terrestrial_tutor.entity.HomeworkEntity;

import java.util.List;
import java.util.Map;

public interface HomeworkService {

    HomeworkEntity saveHomework(HomeworkEntity homework);

//    List<HomeworkEntity> getAllHomeworksPupil();
    List<HomeworkEntity> getAllHomeworksTutor();
    List<AnswerEntity> getPupilAnswers(Long homeworkId, Long pupilId);
    HomeworkEntity getHomeworkById(Long id);
    void deleteHomeworkById(Long id);
    HomeworkEntity save(HomeworkEntity homework);
    HomeworkAnswersDTO checkingAndSaveAnswers(Map<Long, String> answers, Long idHomework);
}
