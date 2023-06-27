package com.example.demo.response;

import com.example.demo.entities.Facility;
import com.example.demo.enums.LaboratoryTestName;
import com.example.demo.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LaboratoryTestResponse {

    private Integer id;

    private Status status;
    private Set<LaboratoryTestName> setLaboratoryTest;
    private String recommendations;
    private LocalDateTime dateTime;
    private BigDecimal price;
    private FacilityResponse facility;
    private Integer patientId;
    private Integer doctorId;
    private Integer labAssistantId;
}
