package com.example.demo.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateLabAssistanRequest(
        @NotNull
        String name,
        @NotNull
        String surname,
        @NotNull
        @Pattern(regexp = "[0-9]{6}", message = "Employee number must have 6 digits.")
        String employeeNumber,
        @NotNull
        Integer facilityId

) {
}
