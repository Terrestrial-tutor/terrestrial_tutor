package com.example.terrestrial_tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/** Дто для возврата результатов дз ученику */

@Getter
@Setter
public class HomeworkAnswersDTO {

    Map<Long, DetailsAnswer> checkingAnswers;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DetailsAnswer{
        Boolean check;
        String rightAnswer;
        //todo ссылка на ютуб
    }

}
