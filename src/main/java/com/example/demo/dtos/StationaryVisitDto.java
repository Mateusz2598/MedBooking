package com.example.demo.dtos;

import com.example.demo.entities.Doctor;
import com.example.demo.entities.Facility;
import com.example.demo.enums.Specialization;
import com.example.demo.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationaryVisitDto {

    private Integer id;
    private Status status;
    @NotNull
    private Specialization specialization;
    @NotNull (message = "Date cannot be null.")
    private LocalDate date;
    @NotNull (message = "Time cannot be null.")
    private Time time;
    private BigDecimal price;
    private String recommendations;
    @NotNull(message = "Doctor ID cannot be null.")
    private Integer doctorId;
    private Integer patientId;
    @NotNull (message = "Facility cannot be null.")
    private FacilityDto facility;
}
