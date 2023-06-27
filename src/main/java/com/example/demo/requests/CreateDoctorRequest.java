package com.example.demo.requests;

import com.example.demo.dtos.*;
import jakarta.validation.Valid;

public record CreateDoctorRequest(

        @Valid
        DoctorDto doctorDto,
        @Valid
        AddressDto addressDto) {
}
