package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.CompletedTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedTaskRepository extends JpaRepository<CompletedTaskEntity, Long> {
}
