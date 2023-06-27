package com.example.demo.dtos;

import com.example.demo.entities.Address;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.PESEL;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PatientDto {

    private Integer id;
    @NotNull (message = "Name cannot be null.")
    @NotBlank
    private String name;
    @NotNull(message = "Surname cannot be null.")
    @NotBlank
    private String surname;
    @PESEL
    private String pesel;
    @NotNull(message = "NumberPhone cannot be null.")
    @Pattern(regexp = ".[0-9]{11}", message = "Incorrect phone number. " +
            "Correct number must contain + and 11 digits. Correct number is +48569856987")
    private String numberPhone;
    @NotNull(message = "E-mail cannot be null.")
    @NotBlank
    @Email
    private String mail;
    private AddressDto address;
    private List<Integer> setIdConsultations;
    private List<Integer> setIdStationaryVisits;
}
