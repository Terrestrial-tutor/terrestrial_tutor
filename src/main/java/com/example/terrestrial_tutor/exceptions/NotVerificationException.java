package com.example.terrestrial_tutor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotVerificationException extends RuntimeException {
    public NotVerificationException(String message) {
        super(message);
    }
}
