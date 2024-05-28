package com.example.terrestrial_tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * Класс DTO ответов на дз
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkAnswersDTO {

    Map<Long, DetailsAnswer> checkingAnswers;
    Integer attemptCount;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DetailsAnswer {
        String pupilAnswer;
        String rightAnswer;
    }

}
