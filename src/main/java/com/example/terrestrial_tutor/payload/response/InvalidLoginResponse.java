package com.example.terrestrial_tutor.payload.response;

import lombok.Getter;

/**
 * Ответ авторизации
 */
@Getter
public class InvalidLoginResponse {
    private final String username;
    private final String password;
    private final String verification;

    public InvalidLoginResponse() {
        this.verification = "Not verification";
        this.username = "Invalid username";
        this.password = "Invalid password";
    }
}
