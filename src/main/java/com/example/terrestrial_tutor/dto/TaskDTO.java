package com.example.terrestrial_tutor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskDTO {
    Long id;
    String name;
    String taskText;
    String answer;
    String subject;
    String level1;
    String level2;
    List<String> homeworks;
}
