package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.AttemptEntity;
import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepository extends JpaRepository<AttemptEntity, Long> {
    AttemptEntity findAttemptEntityByPupilAndHomeworkAndAttemptNumber(PupilEntity pupil, HomeworkEntity homework, int number);
}
