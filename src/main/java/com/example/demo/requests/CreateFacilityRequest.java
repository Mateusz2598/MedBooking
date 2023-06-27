package com.example.demo.requests;

import com.example.demo.dtos.AddressDto;
import com.example.demo.dtos.FacilityDto;
import jakarta.validation.Valid;
import lombok.Data;

public record CreateFacilityRequest(

        @Valid
        FacilityDto facilityDto,
        @Valid
        AddressDto addressDto) {
}
