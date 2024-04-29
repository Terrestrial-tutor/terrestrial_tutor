package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.entity.*;
import com.example.terrestrial_tutor.entity.enums.TaskCheckingType;
import com.example.terrestrial_tutor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class HomeworkFacade {

    @Autowired
    TaskFacade taskFacade;
    @Autowired
    SubjectService subjectService;
    @Autowired
    HomeworkService homeworkService;
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
        List<TaskDTO> tasks = new LinkedList<>();
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
                .map(id -> pupilService.findPupilById(id)).collect(Collectors.toSet()));

        if (homeworkDTO.getTasksCheckingTypes() != null) {
            Map<TaskEntity, TaskCheckingType> tasksCheckingTypes = new HashMap<>();
            homeworkDTO.getTasksCheckingTypes().forEach((key, value) ->
            {
                TaskEntity task = taskService.getTaskById(key);
                tasksCheckingTypes.put(task, TaskCheckingType.valueOf(value));
            });
            List<CompletedTaskEntity> completedTaskEntities = new ArrayList<>();
            tasksCheckingTypes.forEach((task, check) -> {
                if (completedTaskService.getByTask(task.getId()) != null) {
                    completedTaskEntities.add(completedTaskService.getByTask(task.getId()));
                } else {
                    completedTaskEntities.add(new CompletedTaskEntity(task, check));
                }
            });
            homework.setCompletedTaskEntities(completedTaskEntities);
        }

        return homework;
    }
}
