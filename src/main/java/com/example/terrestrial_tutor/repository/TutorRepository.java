package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий сущности репетитора
 */
public interface TutorRepository extends JpaRepository<TutorEntity, Long> {
    /**
     * Поиск репетитора по id
     *
     * @param id id
     * @return репетитор
     */
    TutorEntity findTutorEntityById(Long id);

    /**
     * Поиск репетитора по логину
     *
     * @param username логин
     * @return репетитор
     */

    TutorEntity findTutorEntityByUsername(String username);
}
