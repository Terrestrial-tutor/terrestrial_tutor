package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    SubjectEntity findSubjectEntityById(Long id);

    List<SubjectEntity> findSubjectEntitiesByTutorsIn(List<TutorEntity> tutors);

    SubjectEntity findSubjectEntityByName(String name);
}
