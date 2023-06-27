package com.example.demo.response;

import com.example.demo.dtos.MedicinDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PrescriptionResponse {

    private String namePatient;
    private String nameDoctor;
    private String code;
    private LocalDate issureDate;
    private List<MedicinResponse> medicin;

}
