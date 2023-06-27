package com.example.demo.dtos;

import com.example.demo.entities.Address;
import com.example.demo.entities.Doctor;
import com.example.demo.entities.LabAssistant;
import com.example.demo.entities.StationaryVisit;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacilityDto {


    private Integer id;
    @NotNull
    private String name;
    private Address address;
    private Set<StationaryVisitDto> stationaryVisit;
    private Set<DoctorDto> doctorSet;
    private Set<LabAssistantDto> labAssistants;

}

