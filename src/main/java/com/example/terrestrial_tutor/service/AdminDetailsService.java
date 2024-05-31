package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.AdminEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Сервис авторизации администратора
 */
public interface AdminDetailsService extends UserDetailsService {
    /**
     * Загрузка пользователя по логину
     *
     * @param username логин
     * @return пользователь
     */
    UserDetails loadUserByUsername(String username);

    /**
     * Загрузка администратора по id
     *
     * @param id id
     * @return администратор
     */

    AdminEntity loadAdminById(Long id);
}
