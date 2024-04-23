package com.example.terrestrial_tutor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/** Дто для возврата результатов дз ученику */

@Getter
@Setter
public class HomeworkAnswerDTO {

    Map<Long, Boolean> checkingAnswers;

}
