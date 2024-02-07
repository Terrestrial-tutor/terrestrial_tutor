package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Long> {
    HomeworkEntity findHomeworkEntityById(Long id);
}
