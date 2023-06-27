package com.example.demo.requests;

import com.example.demo.dtos.AddressDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UpdatePatientRequest(
        @Pattern(regexp = ".[0-9]{11}", message = "Incorrect phone number. " +
                "Correct number must contain + and 11 digits. Correct number is +48569856987")
        String numberPhone,
        @Email
        String mail,
        @Valid
        AddressDto addressDto) {
}
