package com.example.demo.entities;

import com.example.demo.enums.Specialization;
import com.example.demo.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultationsPhone {

    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    private LocalDate date;
    private Time time;
    private BigDecimal price;
    private String recommendations;
    private Integer doctorId;
    private Integer patientId;
}
