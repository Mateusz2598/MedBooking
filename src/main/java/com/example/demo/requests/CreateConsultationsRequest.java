package com.example.demo.requests;

import com.example.demo.enums.Specialization;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;

public record CreateConsultationsRequest(

        @NotNull(message = "Specialization cannot be null. Add specialization.")
        Specialization specialization,
        @NotNull(message = "Date cannot be null. Add date.")
        LocalDate date,
        @NotNull(message = "Time cannot be null. Add time.")
        Time time,
        BigDecimal price,
        String recommendations,
        @NotNull(message = "DoctorId cannot be null. Add Doctor ID.")
        Integer doctorId
) {
}
