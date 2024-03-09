package com.example.terrestrial_tutor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
    String name;
    int checking;
    String answerType;
    Long id;
    String taskText;
    String answer;
    String subject;
    String level1;
    String level2;
}
