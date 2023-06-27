package com.example.demo.requests;

import com.example.demo.dtos.AddressDto;
import com.example.demo.dtos.PatientDto;
import jakarta.validation.Valid;
import lombok.Data;

public record CreatePatientRequests(
        @Valid
        PatientDto patientDto,
        @Valid
        AddressDto addressDto) {
}
