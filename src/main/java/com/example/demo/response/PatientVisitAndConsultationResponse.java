package com.example.demo.response;

import com.example.demo.enums.Specialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientVisitAndConsultationResponse {

    private Integer id;
    private String name;
    private String surname;
    private String numberPhone;
    private String mail;
    private LocalDate dateVisit;
    private Time time;
    private Specialization specialization;

}
