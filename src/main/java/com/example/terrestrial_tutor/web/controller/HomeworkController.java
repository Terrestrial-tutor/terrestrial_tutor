package com.example.terrestrial_tutor.web.controller;

import com.example.terrestrial_tutor.TerrestrialTutorApplication;
import com.example.terrestrial_tutor.annotations.Api;
import com.example.terrestrial_tutor.dto.HomeworkAnswersDTO;
import com.example.terrestrial_tutor.dto.SelectionDTO;
import com.example.terrestrial_tutor.dto.facade.HomeworkFacade;
import com.example.terrestrial_tutor.entity.AnswerEntity;
import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.service.HomeworkService;
import com.example.terrestrial_tutor.service.SubjectService;
import com.example.terrestrial_tutor.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Api
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;
    @Autowired
    TaskService taskService;
    @Autowired
    HomeworkFacade homeworkFacade;
    @Autowired
    SubjectService subjectService;

    static final Logger log =
            LoggerFactory.getLogger(TerrestrialTutorApplication.class);

    //todo контроллер для обработки завершенной домашки

    @PostMapping("/homework/save")
    public ResponseEntity<HomeworkDTO> saveHomework(@RequestBody HomeworkDTO homeworkDTO) {
        HomeworkEntity newHomework = homeworkService.saveHomework(homeworkFacade.homeworkDTOToHomework(homeworkDTO));
        HomeworkDTO newHomeworkDTO = homeworkFacade.homeworkToHomeworkDTO(newHomework);
        return new ResponseEntity<>(newHomeworkDTO, HttpStatus.OK);
    }

//    @PostMapping("/homework/add/tasks/{HWId}")
//    public HttpStatus addTasksToHomework(@RequestBody List<Long> taskIds, @PathVariable Long HWId) {
//        HomeworkEntity homework = homeworkService.getHomeworkById(HWId);
//        List<TaskEntity> tasks = new ArrayList<>();
//        for (Long taskId : taskIds) {
//            TaskEntity task = taskService.getTaskById(taskId);
//            List<HomeworkEntity> taskHomeworks = task.getHomeworks();
//            if (!taskHomeworks.contains(homework)) {
//                taskHomeworks.add(homework);
//                task.setHomeworks(taskHomeworks);
//                taskService.save(task);
//            }
//            tasks.add(task);
//        }
//
//        for (TaskEntity task : homework.getTasks()) {
//            if (!tasks.contains(task)) {
//                List<HomeworkEntity> taskHomeworks = task.getHomeworks();
//                taskHomeworks.remove(homework);
//                taskService.save(task);
//            }
//        }
//
//        homework.setTasks(tasks);
//        homeworkService.save(homework);
//        return HttpStatus.OK;
//    }

    /**
     * Контроллер для отдачи случайной выборки заданий по заданным данным и формированием в дз
     *
     * @param selectionDTO - входные данные, см. "SelectionDTO"
     * @return лист выборки
     */
    @PostMapping("/homework/selection")
    public ResponseEntity<List<TaskEntity>> getTasksSelection(@RequestBody SelectionDTO selectionDTO) {
        SubjectEntity currentSubject = subjectService.findSubjectByName(selectionDTO.getSubject());
        List<TaskEntity> result = taskService.getSelectionTask(selectionDTO.getChoices(), currentSubject);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/pupil/homework/{homeworkId}")
    public ResponseEntity<HomeworkAnswersDTO> getCheckingAnswers(@RequestBody Map<Long, String> answers,
                                                                 @PathVariable Long homeworkId) {
        HomeworkAnswersDTO homeworkAnswersDTO = homeworkService.checkingAndSaveAnswers(answers, homeworkId);
        return new ResponseEntity<>(homeworkAnswersDTO, HttpStatus.OK);
    }

    @GetMapping("/homework/{id}")
    public ResponseEntity<HomeworkDTO> getHomeworkById(@PathVariable Long id) {
        HomeworkEntity homework = homeworkService.getHomeworkById(id);
        return new ResponseEntity<>(homeworkFacade.homeworkToHomeworkDTO(homework), HttpStatus.OK);
    }

    /**
     * Контроллер для отдачи списка выполненных дз ученика
     *
     * @param id - id ученика
     * @return список выполненных дз ученика
     */
    @GetMapping("/pupil/{id}/homework/completed")
    public ResponseEntity<Map<Long, Integer>> getCompletedHomework(@PathVariable Long id) {
        return new ResponseEntity<>(homeworkService.getCompletedHomework(id), HttpStatus.OK);
    }

    /**
     * Контроллер для отдачи результатов дз по определенной попытке
     *
     * @param id      - id дз
     * @param idPupil - id ученика
     * @param attempt - номер попытки
     * @return - результаты дз по определенной попытке
     */
    @GetMapping("/pupil/homework/{id}/answers/{idPupil}/{attempt}")
    public ResponseEntity<HomeworkAnswersDTO> getHomeworkAnswers(@PathVariable Long id, @PathVariable Long idPupil,
                                                                 @PathVariable int attempt) {
        HomeworkAnswersDTO homeworkAnswersDTO = homeworkService.getPupilAnswers(id, idPupil, attempt);
        return new ResponseEntity<>(homeworkAnswersDTO, HttpStatus.OK);
    }

    @DeleteMapping("/homework/delete/{id}")
    public HttpStatus deleteHomeworkById(@PathVariable Long id) {
        homeworkService.deleteHomeworkById(id);
        return HttpStatus.OK;
    }

//    @GetMapping("/homework/empty/{id}")
//    public ResponseEntity<Boolean> isHomeworkEmpty(@PathVariable Long id) {
//        return new ResponseEntity<>(homeworkService.isHomeworkEmpty(id), HttpStatus.OK);
//    }
}
