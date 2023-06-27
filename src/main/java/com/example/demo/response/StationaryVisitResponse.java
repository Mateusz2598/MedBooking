package com.example.demo.response;


import com.example.demo.enums.Specialization;
import com.example.demo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class StationaryVisitResponse {

    private Integer id;
    private Status status;
    private Specialization specialization;
    private LocalDate date;
    private Time time;
    private BigDecimal price;
    private String recommendations;
    private Integer doctorId;
    private Integer patientId;
    private FacilityResponse facility;
}
