package com.example.terrestrial_tutor.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Запрос авторизации
 */
@Data
public class LoginRequest {

    @NotEmpty(message = "Username can not be empty")
    private String username;
    @NotEmpty(message = "Password can not be empty")
    private String password;


}
