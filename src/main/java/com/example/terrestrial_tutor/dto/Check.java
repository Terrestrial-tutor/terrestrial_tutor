package com.example.terrestrial_tutor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Check {
    Long id;
    String username;
    String name;
    String surname;
    String patronymic;
    Date date;
    String role;
}
