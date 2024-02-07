package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<TutorEntity, Long> {
    TutorEntity findTutorEntityById(Long id);
}
