package com.abrajner.plagiarismdetector.restapi.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(final String message) {
        super(message);
    }
    
    public FileNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}