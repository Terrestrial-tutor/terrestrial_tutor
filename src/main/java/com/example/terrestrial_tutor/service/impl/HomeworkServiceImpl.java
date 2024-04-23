package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.dto.HomeworkAnswerDTO;
import com.example.terrestrial_tutor.entity.*;
import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.entity.enums.TaskCheckingType;
import com.example.terrestrial_tutor.exceptions.CustomException;
import com.example.terrestrial_tutor.repository.CompletedTaskRepository;
import com.example.terrestrial_tutor.repository.HomeworkRepository;
import com.example.terrestrial_tutor.repository.PupilRepository;
import com.example.terrestrial_tutor.repository.TaskRepository;
import com.example.terrestrial_tutor.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
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
    public HomeworkEntity getHomeworkById(Long id){
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

    public HomeworkAnswerDTO checkingAnswers(Map<Long, String> answers, Long idHomework) {
        HomeworkEntity homework = getHomeworkById(idHomework);
        Set<CompletedTaskEntity> completedTaskEntities = homework.getCompletedTaskEntities();
        Map<Long, Boolean> checkingAnswers = new HashMap<>();
        for (Map.Entry<Long, String> entry : answers.entrySet()){
            CompletedTaskEntity completedTaskEntity = null;
            for(CompletedTaskEntity completedTaskEntity1 : completedTaskEntities){
                if(completedTaskEntity1.getTask().getId().equals(entry.getKey())){
                    completedTaskEntity = completedTaskEntity1;
                    break;
                }
            }
            if(completedTaskEntity == null){
                throw new CustomException("There is no such task in homework");
            }
            switch (completedTaskEntity.getTaskCheckingType()){
                case AUTO:
                    switch (taskService.getTaskById(entry.getKey()).getAnswerType()){
                        case "test":
                            AnswerEntity answerEntity = new AnswerEntity();
                            answerEntity.setAnswer(entry.getValue());
                            answerEntity.setHomework(homework);
                            answerEntity.setPupil((PupilEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                            answerService.addNewAnswer(answerEntity);
                            if(entry.getValue().equals(taskService.getTaskById(entry.getKey()).getAnswer().get(0))){
                                checkingAnswers.put(entry.getKey(), true);
                            }
                            else{
                                checkingAnswers.put(entry.getKey(), false);
                            }
                        case "":
                            //todo сделать другие типы ответов
                        default:
                            throw new CustomException("Task does not have a answer type");
                    }
                case INSTANCE:
                    //todo сделать другие типы проверок
                case MANUALLY:
                default:
                    throw new CustomException("Task does not have a review type");
            }
        }
        HomeworkAnswerDTO homeworkAnswerDTO = new HomeworkAnswerDTO();
        homeworkAnswerDTO.setCheckingAnswers(checkingAnswers);
        return homeworkAnswerDTO;
    }
//    public List<HomeworkEntity> getAllHomeworksPupil(){
//        PupilEntity pupil = (PupilEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return homeworkRepository.findHomeworkEntitiesByPupils(new PupilEntity[]{pupil});
//    }

    public List<HomeworkEntity> getAllHomeworksTutor(){
        TutorEntity tutor = (TutorEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return homeworkRepository.findHomeworkEntitiesByTutor(tutor);
    }
}
