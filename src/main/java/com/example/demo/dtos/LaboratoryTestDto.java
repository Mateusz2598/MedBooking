package com.example.demo.dtos;

import com.example.demo.entities.Facility;
import com.example.demo.entities.LabAssistant;
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
public class LaboratoryTestDto {

    private Integer id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull(message = "setLaboratoryTest cannot be null.")
    private Set<LaboratoryTestName> setLaboratoryTest;
    private String recommendations;
    @NotNull(message = "Bad datatime value. Correct value is e.g. 2010-12-28T07:30")
    private LocalDateTime dateTime;
    private BigDecimal price;
    @NotNull(message = "Facility cannot be null. Add facility id in request")
    private FacilityDto facility;
    @NotNull(message = "Patient cannot be null. Add patient id in request")
    private Integer patientId;
    private Integer doctorId;
    private Integer labAssistantId;
}
