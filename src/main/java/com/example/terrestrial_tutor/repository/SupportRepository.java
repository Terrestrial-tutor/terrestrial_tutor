package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SupportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRepository extends JpaRepository<SupportEntity, Long> {
    SupportEntity findSupportEntityById(Long id);
    SupportEntity findSupportEntityByUsername(String username);
}
