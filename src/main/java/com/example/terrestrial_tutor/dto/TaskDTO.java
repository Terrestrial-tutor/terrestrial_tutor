package com.example.terrestrial_tutor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    List<String> homeworks;
}