package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.AnswerEntity;
import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    List<AnswerEntity> findAnswerEntitiesByPupilAndHomework(PupilEntity pupil, HomeworkEntity homework);
    List<AnswerEntity> findAnswerEntitiesByPupilAndHomeworkAndTask(PupilEntity pupil, HomeworkEntity homework, TaskEntity task);
}
