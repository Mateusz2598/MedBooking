package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private Integer id;
    @NotNull(message = "City cannot be null.")
    @Size(min = 3, max = 60, message = "Wrong city value. Please enter a value between 3 and 60.")
    private String city;
    private String street;
    @NotNull(message = "ZipCode cannot be null.")
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "Invalid zipcode. The correct code is 22-222.")
    private String zipCode;
    private String numberStreet;
    @NotNull(message = "Nr flat cannot be null.")
    @NotBlank
    private String numberFlat;
}
