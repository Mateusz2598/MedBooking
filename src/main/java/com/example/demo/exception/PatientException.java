package com.example.demo.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatientException extends RuntimeException {

    private String message;

    private LocalDateTime dateTime;

    public PatientException(String message, LocalDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }
}
