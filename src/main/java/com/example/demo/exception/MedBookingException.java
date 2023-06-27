package com.example.demo.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedBookingException extends RuntimeException{

    private String message;
    private LocalDateTime dateTime;

    public MedBookingException(String name, LocalDateTime dateTime) {
        this.message = name;
        this.dateTime = dateTime;
    }
    public MedBookingException(String name) {
        this.message = name;
    }
}
