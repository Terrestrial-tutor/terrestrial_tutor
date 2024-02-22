package com.example.terrestrial_tutor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Класс для входным параметров для выборки заданий случайным образом по заданным параметрам
 * subject - предмет заданий
 * choices - словарь, где ключ - название темы задания (1 или 2 уровня),
 *           значение - кол-во нужных заданий по этой теме
 */
@Getter
@Setter
public class SelectionDTO {
    String subject;

    Map<String, Integer> choices;
}
