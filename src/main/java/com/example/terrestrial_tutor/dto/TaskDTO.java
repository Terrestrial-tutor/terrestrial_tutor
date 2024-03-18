package com.example.terrestrial_tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TaskDTO {
    String name;
    int checking;
    String answerType;
    String taskText;
    String answer;
    String subject;
    String level1;
    String level2;
    String file;
    List<String> homeworks;
}
