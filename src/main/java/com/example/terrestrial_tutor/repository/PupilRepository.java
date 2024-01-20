package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.PupilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PupilRepository extends JpaRepository<PupilEntity, Long> {
    PupilEntity findPupilEntityById(Long id);
}
