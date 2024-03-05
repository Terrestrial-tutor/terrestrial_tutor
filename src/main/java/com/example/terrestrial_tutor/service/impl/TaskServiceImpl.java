package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.repository.TaskRepository;
import com.example.terrestrial_tutor.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    /**
     * Функция вывода листа заданий по учебному прдмету и уровню выбора 1
     *
     * @param subject - учебный предмет
     * @param level1  - уровень выбора 1
     * @return Лист заданий по учебному прдмету и уровню выбора 1
     */
    public List<TaskEntity> getTasksBySubjectAndLevel1(SubjectEntity subject, String level1) {
        return taskRepository.findTaskEntitiesBySubjectAndLevel1(subject, level1);
    }

    /**
     * Функция вывода листа заданий по учебному прдмету и уровню выбора 2 | null, если у предмета нет 2 уровня
     *
     * @param subject - учебный предмет
     * @param level2  - уровень выбора 2
     * @return Лист заданий по учебному прдмету и уровню выбора 2 | null, если у предмета нет 2 уровня
     */
    public List<TaskEntity> getTasksBySubjectAndLevel2(SubjectEntity subject, String level2) {
        if (subject.getCountLevel() > 1)
            return taskRepository.findTaskEntitiesBySubjectAndLevel2(subject, level2);
        else
            return null;
    }
}
