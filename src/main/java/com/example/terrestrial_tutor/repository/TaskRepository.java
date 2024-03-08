package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    TaskEntity findTaskEntityById(Long id);

    List<TaskEntity> findTaskEntitiesBySubjectAndLevel1(SubjectEntity subject, String level1);
    List<TaskEntity> findTaskEntitiesBySubjectAndLevel2(SubjectEntity subject, String level2);
    List<TaskEntity> findTaskEntitiesBySubjectAndLevel1AndLevel2(SubjectEntity subject, String level1, String level2);
}
