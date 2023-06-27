package com.example.demo.response;

import com.example.demo.dtos.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientUpdateResponse {
    private Integer id;
    private String name;
    private String surname;
    private String pesel;
    private String numberPhone;
    private String mail;
    private AddressDto addressDto;
}
