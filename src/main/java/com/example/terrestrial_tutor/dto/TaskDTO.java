package com.example.terrestrial_tutor.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class TaskDTO {
    Long id;
    final String name;
    final int checking;
    final String answerType;
    final String taskText;
    final List<String> answers;
    final String subject;
    final String level1;
    final String level2;
    final String table;
    Set<String> files;
}
