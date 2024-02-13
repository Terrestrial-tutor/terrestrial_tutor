package com.example.terrestrial_tutor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PupilDTO {
    Long id;
    Double balance;
    List<String> homeworks;
    int price;
    List<String> subjects;
    List<String> tutors;
    String username;
}
