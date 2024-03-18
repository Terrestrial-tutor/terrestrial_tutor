package com.example.terrestrial_tutor.payload.request;

import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
public class HomeworkAddRequest {
    @NonNull
    String name;

    //tasks

    //List<Integer> = new ArrayList<Integer>();

    @NonNull
    Time soluteTime;

    @NonNull
    Long pupilId;

    //    @NonNull
    //    Long tutorId;

    @NonNull
    List<Long> tasksId = new ArrayList<>();
}
