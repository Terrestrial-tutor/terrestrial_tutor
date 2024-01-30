package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.TaskTablesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTablesRepository extends JpaRepository<TaskTablesEntity, Long> {
    TaskTablesEntity findTaskTablesEntityById (Long id);
}
