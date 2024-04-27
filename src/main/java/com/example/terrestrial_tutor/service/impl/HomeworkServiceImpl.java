package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.dto.HomeworkAnswersDTO;
import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.dto.facade.HomeworkFacade;
import com.example.terrestrial_tutor.entity.*;
import com.example.terrestrial_tutor.entity.enums.TaskCheckingType;
import com.example.terrestrial_tutor.exceptions.CustomException;
import com.example.terrestrial_tutor.repository.*;
import com.example.terrestrial_tutor.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
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
    PupilService pupilService;

    @Autowired
    AnswerService answerService;

    @Autowired
    TutorService tutorService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    CompletedTaskServiceImpl completedTaskService;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    HomeworkFacade homeworkFacade;

    public HomeworkEntity saveHomework(HomeworkEntity homework) {
        if (!homework.getTutor().getHomeworkList().contains(homework)) {
            Set<HomeworkEntity> currentTutorHomeworks = homework.getTutor().getHomeworkList();
            currentTutorHomeworks.add(homework);
            homework.getTutor().setHomeworkList(currentTutorHomeworks);
        }
        HomeworkEntity finalHomework = homework;
        homework.setCompletedTaskEntities(homework.getCompletedTaskEntities().stream().peek(task -> {
            task.setHomework(finalHomework);
        }).collect(Collectors.toSet()));
        homework = homeworkRepository.save(homework);
        tutorService.updateTutor(homework.getTutor());
        return homework;
    }

    public HomeworkEntity getHomeworkById(Long id) {
        return homeworkRepository.findHomeworkEntityById(id);
    }

    public void deleteHomeworkById(Long id) {
        HomeworkEntity homework = getHomeworkById(id);
        TutorEntity tutor = homework.getTutor();
        tutor.getHomeworkList().remove(homework);
        tutorService.updateTutor(tutor);
//        homework.setTutor(null);
        homeworkRepository.delete(homeworkRepository.findHomeworkEntityById(id));
    }

    public HomeworkEntity save(HomeworkEntity homework) {

        return homeworkRepository.save(homework);
    }

    public HomeworkAnswersDTO getPupilAnswers(Long homeworkId, Long pupilId, int attempt) {
        PupilEntity pupilEntity = pupilService.findPupilById(pupilId);
        HomeworkEntity homework = getHomeworkById(homeworkId);
        List<AnswerEntity> answers = answerRepository.findAnswerEntitiesByPupilAndHomeworkAndAttemptNumber(pupilEntity, homework, attempt);
        int attempts = getLastAttempt(homework, pupilEntity);
        Map<Long, HomeworkAnswersDTO.DetailsAnswer> checkingAnswers = new HashMap<>();
        if (attempts >= attempt) {
            for (AnswerEntity answer : answers) {
                checkingAnswers.put(answer.getTask().getId(), new HomeworkAnswersDTO.DetailsAnswer(answer.getAnswer(), answer.getTask().getAnswer().get(0)));
            }
        } else {
            throw new CustomException("Attempt number not found");
        }
        return new HomeworkAnswersDTO(checkingAnswers);

    }

    public Map<HomeworkDTO, Integer> getCompletedHomework(Long id) {
        PupilEntity pupil = pupilService.findPupilById(id);
        Set<PupilEntity> pupilEntities = new HashSet<>();
        pupilEntities.add(pupil);
        List<HomeworkEntity> homeworkEntities = homeworkRepository.findHomeworkEntitiesByPupils(pupilEntities);
        Map<HomeworkDTO, Integer> result = new HashMap<>();
        for (HomeworkEntity he : homeworkEntities) {
            int attempts = getLastAttempt(he, pupil);
            if (attempts > 0) {
                result.put(homeworkFacade.homeworkToHomeworkDTO(he), attempts);
            }
        }
        return result;
    }

    public int getLastAttempt(HomeworkEntity homework, PupilEntity pupil) {
        List<AnswerEntity> answers = answerRepository.findAnswerEntitiesByPupilAndHomework(pupil, homework);
        if (answers.isEmpty()) {
            return 0;
        } else {
            int result = 0;
            Map<Integer, Integer> count = new HashMap<>();
            for (AnswerEntity answer : answers) {
                result = Math.max(result, answer.getAttemptNumber());
                if (count.containsKey(answer.getAttemptNumber())) {
                    int t = count.get(answer.getAttemptNumber());
                    count.put(answer.getAttemptNumber(), t + 1);
                } else {
                    count.put(answer.getAttemptNumber(), 1);
                }
            }
            while (result != 0) {
                if (count.get(result) == homework.getCompletedTaskEntities().size()) {
                    break;
                } else {
                    result--;
                }
            }
            return result;
        }
    }

    public HomeworkAnswersDTO checkingAndSaveAnswers(Map<Long, String> answers, Long idHomework) {
        HomeworkEntity homework = getHomeworkById(idHomework);
        Set<CompletedTaskEntity> completedTaskEntities = homework.getCompletedTaskEntities();
        Map<Long, HomeworkAnswersDTO.DetailsAnswer> checkingAnswers = new HashMap<>();
        PupilEntity pupil = (PupilEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int attemptNumber = getLastAttempt(homework, pupil) + 1;
        for (Map.Entry<Long, String> entry : answers.entrySet()) {
            CompletedTaskEntity completedTaskEntity = null;
            for (CompletedTaskEntity completedTaskEntity1 : completedTaskEntities) {
                if (completedTaskEntity1.getTask().getId().equals(entry.getKey())) {
                    completedTaskEntity = completedTaskEntity1;
                    break;
                }
            }
            if (completedTaskEntity == null) {
                throw new CustomException("There is no such task in homework");
            }
            TaskCheckingType check = completedTaskEntity.getTaskCheckingType();
            TaskEntity task = taskService.getTaskById(entry.getKey());
            boolean isRepeat = !answerRepository.findAnswerEntitiesByPupilAndHomeworkAndTask((PupilEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                    homework, task).isEmpty();
            switch (check.name()) {
                case "AUTO":
                    switch (task.getAnswerType()) {
                        case "Варианты", "Текст или значение", "Код":
                            AnswerEntity answerEntity = new AnswerEntity();
                            answerEntity.setAnswer(entry.getValue());
                            answerEntity.setHomework(homework);
                            answerEntity.setTask(task);
                            answerEntity.setAttemptNumber(attemptNumber);
                            answerEntity.setPupil((PupilEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                            answerService.addNewAnswer(answerEntity);
                            break;
                        default:
                            throw new CustomException("Task does not have a answer type");
                    }
                    break;
                case "INSTANCE":
                    switch (task.getAnswerType()) {
                        case "Варианты", "Текст или значение", "Код":
                            List<AnswerEntity> attempts = answerRepository.findAnswerEntitiesByPupilAndHomeworkAndAttemptNumber(pupil, homework, attemptNumber);
                            answerRepository.deleteAll(attempts);
                            AnswerEntity answerEntity = new AnswerEntity();
                            answerEntity.setAnswer(entry.getValue());
                            answerEntity.setHomework(homework);
                            answerEntity.setAttemptNumber(attemptNumber);
                            answerEntity.setTask(task);
                            answerEntity.setPupil((PupilEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                            answerService.addNewAnswer(answerEntity);
                            String rightAnswer = taskService.getTaskById(entry.getKey()).getAnswer().get(0);
                            checkingAnswers.put(entry.getKey(), new HomeworkAnswersDTO.DetailsAnswer(entry.getValue(), rightAnswer));
                            break;
                        default:
                            throw new CustomException("Task does not have a answer type");
                    }
                    break;
                //todo сделать другие типы проверок
                case "MANUALLY":
                default:
                    throw new CustomException("Task does not have a review type");
            }
        }
        HomeworkAnswersDTO homeworkAnswersDTO = new HomeworkAnswersDTO();
        homeworkAnswersDTO.setCheckingAnswers(checkingAnswers);
        return homeworkAnswersDTO;
    }
//    public List<HomeworkEntity> getAllHomeworksPupil(){
//        PupilEntity pupil = (PupilEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return homeworkRepository.findHomeworkEntitiesByPupils(new PupilEntity[]{pupil});
//    }

    public List<HomeworkEntity> getAllHomeworksTutor() {
        TutorEntity tutor = (TutorEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return homeworkRepository.findHomeworkEntitiesByTutor(tutor);
    }
}
