package com.example.terrestrial_tutor.dto;

import com.example.terrestrial_tutor.entity.SubjectEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
    String name;
    int checking;
    String answerType;
    String taskText;
    String answer;
    String subject;
    String level1;
    String level2;

}
