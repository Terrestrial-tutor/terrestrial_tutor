package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.dto.HomeworkAnswersDTO;
import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.entity.AnswerEntity;
import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;

import java.util.List;
import java.util.Map;

public interface HomeworkService {

    HomeworkEntity saveHomework(HomeworkEntity homework);

//    List<HomeworkEntity> getAllHomeworksPupil();
    List<HomeworkEntity> getAllHomeworksTutor();
    List<HomeworkEntity> getAllHomeworks();
    HomeworkAnswersDTO getPupilAnswers(Long homeworkId, Long pupilId, int attempt);
    HomeworkEntity getHomeworkById(Long id);
    Map<Long, Integer> getCompletedHomework(Long id);
    int getLastAttempt(HomeworkEntity homework, PupilEntity pupil);
    void deleteHomeworkById(Long id);
    HomeworkEntity save(HomeworkEntity homework);
    HomeworkAnswersDTO checkingAndSaveAnswers(Map<Long, String> answers, Long idHomework);
}
