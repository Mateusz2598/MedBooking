package com.example.demo.requests;

import jakarta.validation.constraints.NotNull;

public record PrescriptionRequest(@NotNull String code, @NotNull String pesel) {
}
