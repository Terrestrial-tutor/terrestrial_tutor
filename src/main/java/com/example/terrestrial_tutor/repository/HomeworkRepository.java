package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.HomeworkEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Long> {
    HomeworkEntity findHomeworkEntityById(Long id);
//    List<HomeworkEntity> findHomeworkEntitiesByPupils(PupilEntity[] pupils);
    List<HomeworkEntity> findHomeworkEntitiesByTutor(TutorEntity tutor);

    List<HomeworkEntity> findHomeworkEntitiesByPupils(Set<PupilEntity> pupils);
}
