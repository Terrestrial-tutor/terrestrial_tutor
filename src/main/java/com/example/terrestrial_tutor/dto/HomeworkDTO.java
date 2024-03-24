package com.example.terrestrial_tutor.dto;

import com.example.terrestrial_tutor.entity.enums.TaskCheckingType;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class HomeworkDTO {
    @NonNull
    String name;
    Long targetTime;
    @NonNull
    List<Long> pupilIds = new ArrayList<>();
    @NonNull
    Map<Long, String> tasksCheckingTypes;
    LocalDate deadLine;


}
