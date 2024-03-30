package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.dto.TaskDTO;
import com.example.terrestrial_tutor.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HomeworkFacade {

    @Autowired
    TaskFacade taskFacade;

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
        homework.getTasksCheckingTypes().forEach((task, type) -> dtoTasksCheckingTypes.put(task.getId(), type.name()));
        homeworkDTO.setTasksCheckingTypes(dtoTasksCheckingTypes);
        List<TaskDTO> tasks = new ArrayList<>();
        homework.getTasksCheckingTypes().forEach((task, taskCheckingType) -> tasks.add(taskFacade.taskToTaskDTO(task)));
        homeworkDTO.setTasks(tasks);
        return homeworkDTO;
    }
}
