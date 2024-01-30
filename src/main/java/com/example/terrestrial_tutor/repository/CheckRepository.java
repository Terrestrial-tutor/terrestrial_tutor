package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.CheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CheckRepository extends JpaRepository<CheckEntity, Long> {
    CheckEntity findCheckEntityById(Long id);
    List<CheckEntity> findCheckEntitiesByDate(Date date);

}
