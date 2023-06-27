package com.example.demo.dtos;

import com.example.demo.entities.Doctor;
import com.example.demo.entities.Medicin;
import com.example.demo.entities.Patient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriptionDto {

    private Integer id;
    @Pattern(regexp = "[0-9]{4}", message = "Prescription code must have 4 digits, e.g. 8975")
    private String code;
    private LocalDate issureDate;
    private LocalDate expirationDate;
    @NotNull
    private Set<Integer> medicin;
    @NotNull
    private Integer doctorId;
    @NotNull
    private Integer patientId;
}
