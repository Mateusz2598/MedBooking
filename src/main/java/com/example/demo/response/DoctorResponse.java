package com.example.demo.response;

import com.example.demo.entities.Skills;
import com.example.demo.enums.Specialization;
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
public class DoctorResponse {

    private Integer id;
    private String name;
    private String surname;
    private String employeeNumber;
    private Specialization specialization;
    private Integer addressId;
    private List<Integer> facilityId;
}
