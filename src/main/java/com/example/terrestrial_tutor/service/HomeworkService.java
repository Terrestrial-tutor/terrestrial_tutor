package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.dto.HomeworkAnswersDTO;
import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;

import java.util.List;
import java.util.Map;

/**
 * Сервис для работы с домашними работами
 */
public interface HomeworkService {

    /**
     * Сохранение дз
     *
     * @param homework дз
     * @return сохраненное дз
     */
    HomeworkEntity saveHomework(HomeworkEntity homework);

    /**
     * Поиск всех дз по репетитору
     *
     * @return лист дз
     */
    List<HomeworkEntity> getAllHomeworksTutor();

    /**
     * Поиск всех дз
     *
     * @return лист дз
     */
    List<HomeworkEntity> getAllHomeworks();

    /**
     * Поиск ответов ученика по конкретному дз
     *
     * @param homeworkId id дз
     * @param pupilId    id ученика
     * @param attempt    номер попытки
     * @return дз DTO
     */
    HomeworkAnswersDTO getPupilAnswers(Long homeworkId, Long pupilId, int attempt);

    /**
     * Поиск дз по id
     *
     * @param id id
     * @return дз
     */
    HomeworkEntity getHomeworkById(Long id);

    /**
     * Поиск домашнего задания и последней попытки по ученику
     *
     * @param id id
     * @return мапа ключ - id дз, значение - номер последней попытки по ученику
     */
    Map<Long, Integer> getCompletedHomework(Long id);

    /**
     * Поиск последней попытки домашнего задания ученика
     *
     * @param homework дз
     * @param pupil    ученик
     * @return номер последней попытки
     */
    int getLastAttempt(HomeworkEntity homework, PupilEntity pupil);

    /**
     * Удалить дз по id
     *
     * @param id id
     */
    void deleteHomeworkById(Long id);

    /**
     * Сохранить дз
     *
     * @param homework дз
     * @return сохраненное дз
     */
    HomeworkEntity save(HomeworkEntity homework);
    HomeworkAnswersDTO checkingAndSaveAnswers(Map<Long, String> answers, Long idHomework, int attempt);

    /**
     * Сохранение ответов на дз
     *
     * @param answers    ответы ученика
     * @param idHomework id дз
     * @param attempt    номер попытки прохождения дз
     * @return дз DTO
     */
    HomeworkAnswersDTO checkingAndSaveAnswers(Map<Long, String> answers, Long idHomework, int attempt);
}
