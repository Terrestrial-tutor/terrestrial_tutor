package com.example.terrestrial_tutor.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class HomeworkDTO {
    String name;
    Long targetTime;
    List<Long> pupilIds = new ArrayList<>();
    Map<Long, String> tasksCheckingTypes;
    LocalDate deadLine;
    @NonNull
    String subject;
    List<Long> tasksIds;
}
