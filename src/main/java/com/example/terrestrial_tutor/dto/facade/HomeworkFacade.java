package com.example.terrestrial_tutor.dto.facade;

import com.example.terrestrial_tutor.dto.HomeworkDTO;
import com.example.terrestrial_tutor.entity.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HomeworkFacade {

    public HomeworkDTO homeworkToHomeworkDTO(HomeworkEntity homework) {
        HomeworkDTO homeworkDTO = new HomeworkDTO(homework.getSubject().getName());
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
        homeworkDTO.setTasksIds(homework.getTasks().
                stream().
                map(TaskEntity::getId).
                toList());
        return homeworkDTO;
    }
}
