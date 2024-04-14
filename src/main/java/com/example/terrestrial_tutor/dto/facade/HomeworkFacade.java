package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.entity.*;
import com.example.terrestrial_tutor.entity.enums.TaskCheckingType;
import com.example.terrestrial_tutor.service.CompletedTaskService;
import com.example.terrestrial_tutor.service.PupilService;
import com.example.terrestrial_tutor.service.SubjectService;
import com.example.terrestrial_tutor.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HomeworkFacade {

    @Autowired
    TaskFacade taskFacade;
    @Autowired
    SubjectService subjectService;
    @Autowired
    PupilService pupilService;
    @Autowired
    TaskService taskService;
    @Autowired
    CompletedTaskService completedTaskService;

    public HomeworkDTO homeworkToHomeworkDTO(HomeworkEntity homework) {
        HomeworkDTO homeworkDTO = new HomeworkDTO();
        homeworkDTO.setId(homework.getId());
        homeworkDTO.setSubject(homework.getSubject().getName());
        homeworkDTO.setName(homework.getName());
        homeworkDTO.setDeadLine(homework.getDeadLine());
        homeworkDTO.setPupilIds(homework.getPupils().
                stream().
                map(PupilEntity::getId).
                toList());
        homeworkDTO.setTargetTime(homework.getTargetTime());
        Map<Long, String> dtoTasksCheckingTypes = new HashMap<>();
        homework.getCompletedTaskEntities().forEach(task -> dtoTasksCheckingTypes.put(task.getTask().getId(), task.getTaskCheckingType().name()));
        homeworkDTO.setTasksCheckingTypes(dtoTasksCheckingTypes);
        List<TaskDTO> tasks = new ArrayList<>();
        homework.getCompletedTaskEntities().forEach(task -> tasks.add(taskFacade.taskToTaskDTO(task.getTask())));
        homeworkDTO.setTasks(tasks);
        return homeworkDTO;
    }

    public HomeworkEntity homeworkDTOToHomework(HomeworkDTO homeworkDTO) {
        HomeworkEntity homework = new HomeworkEntity();
        homework.setId(homeworkDTO.getId());
        homework.setName(homeworkDTO.getName());
        homework.setDeadLine(homeworkDTO.getDeadLine());
        homework.setSubject(subjectService.findSubjectByName(homeworkDTO.getSubject()));
        TutorEntity tutor = (TutorEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        homework.setTutor(tutor);
        homework.setPupils(homeworkDTO.getPupilIds().stream()
                .map(id -> pupilService.findPupilById(id)).collect(Collectors.toList()));

        if (homeworkDTO.getTasksCheckingTypes() != null) {
            Map<TaskEntity, TaskCheckingType> tasksCheckingTypes = new HashMap<>();
            homeworkDTO.getTasksCheckingTypes().forEach((key, value) ->
            {
                TaskEntity task = taskService.getTaskById(key);
                tasksCheckingTypes.put(task, TaskCheckingType.valueOf(value));
            });
            List<CompletedTaskEntity> completedTaskEntities = new ArrayList<>();
            tasksCheckingTypes.forEach((task, check) -> completedTaskEntities.add(new CompletedTaskEntity(task, check)));
            homework.setCompletedTaskEntities(completedTaskEntities);
        }

        return homework;
    }
}
