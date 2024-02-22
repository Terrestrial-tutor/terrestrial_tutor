package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    SubjectEntity findSubjectEntityById(Long id);

    SubjectEntity findSubjectEntityByName(String name);
}
