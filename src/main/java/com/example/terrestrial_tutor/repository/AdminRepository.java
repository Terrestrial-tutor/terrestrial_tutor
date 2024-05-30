package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий сущности администратора
 */
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    /**
     * Поиск администратора по id
     *
     * @param id id
     * @return сущность администратора
     */
    AdminEntity findAdminEntityById(Long id);

    /**
     * Поиск администратора по логину
     *
     * @param username логин
     * @return сущность администратора
     */

    AdminEntity findAdminEntityByUsername(String username);
}
