package com.example.demo.dtos;

import com.example.demo.entities.*;
import com.example.demo.entities.Skills;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {

    private Integer id;
    @NotNull(message = "Name cannot be null.")
    @NotBlank
    private String name;
    @NotNull(message = "Surname cannot be null.")
    @NotBlank
    private String surname;
    @NotNull
    @Pattern(regexp = "[0-9]{6}", message = "Employee number must have 6 digits.")
    private String employeeNumber;
    private Set<StationaryVisitDto> stationaryVisit;
    private Set<ConsultationsPhoneDto> eConsultations;
    @NotNull
    private SkillsDto skills;
    private AddressDto address;
    @NotNull
    private Set<FacilityDto> facility;
}
