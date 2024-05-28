package com.example.terrestrial_tutor.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Ответ регистрации
 */
@Data
@AllArgsConstructor
public class RegistrationSuccess {
    private String success;
}
