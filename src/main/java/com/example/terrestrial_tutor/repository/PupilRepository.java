package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.PupilEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PupilRepository extends JpaRepository<PupilEntity, Long> {
    PupilEntity findPupilEntityById(Long id);
    PupilEntity findPupilEntityByUsername(String username);
}
