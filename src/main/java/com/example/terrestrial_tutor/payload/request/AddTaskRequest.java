package com.example.terrestrial_tutor.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTaskRequest {
    String name;
    int checking;
    String answerType;
    String taskText;
    String answer;
    String subject;
    String level1;
    String level2;
}
