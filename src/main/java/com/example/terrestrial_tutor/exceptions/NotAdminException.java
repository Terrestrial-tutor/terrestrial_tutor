package com.example.terrestrial_tutor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAdminException extends RuntimeException {
    public NotAdminException() {
        super("This is not an admin login form");
    }
}
