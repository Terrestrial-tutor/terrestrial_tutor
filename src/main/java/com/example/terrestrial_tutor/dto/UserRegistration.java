package com.example.terrestrial_tutor.dto;

import com.example.terrestrial_tutor.enums.Role;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserRegistration {
    String username;
    String password;
    String role;
}