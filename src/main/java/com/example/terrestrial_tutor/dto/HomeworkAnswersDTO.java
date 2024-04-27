package com.example.terrestrial_tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/** Дто для возврата результатов дз ученику */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkAnswersDTO {

    Map<Long, DetailsAnswer> checkingAnswers;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DetailsAnswer{
        String pupilAnswer;
        String rightAnswer;
        //todo ссылка на ютуб
    }

}
