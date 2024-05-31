package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.CheckEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Сервис для работы с проверками(согласование с администратором регистрации нового пользователя)
 */
public interface CheckService {
    /**
     * Получение всех проверок
     *
     * @return лист проверок
     */
    List<CheckEntity> getAllChecks();

    /**
     * Добавление проверки пользователю
     *
     * @param newUser пользователь
     * @return проверка
     */

    CheckEntity addCheck(UserDetails newUser);

    /**
     * Удаление проверки
     *
     * @param id id
     * @return удаленная проверка
     */

    CheckEntity deleteCheck(Long id);

    /**
     * Поиск проверки по id
     *
     * @param id id
     * @return найденная проверка
     */

    CheckEntity findCheckById(Long id);
}
