package com.example.demo.dtos;

import com.example.demo.entities.Facility;
import jakarta.validation.constraints.*;
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
public class LabAssistantDto {

    private Integer id;
    @NotNull (message = "Name cannot be null.")
    @NotBlank
    private String name;
    @NotNull(message = "Surname cannot be null.")
    @NotBlank
    private String surname;
    @NotNull
    @Pattern(regexp = "[0-9]{6}", message = "Employee number must have 6 digits.")
    private String employeeNumber;
    private Set<Integer> listIdLaboratoryTest;
    @NotNull(message = "Facility cannot be null.")
    private FacilityDto facility;
}
