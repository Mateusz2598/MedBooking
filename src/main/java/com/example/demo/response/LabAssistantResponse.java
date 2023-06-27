package com.example.demo.response;

import com.example.demo.entities.Facility;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

public class LabAssistantResponse {

    private Integer id;
    private String name;
    private String surname;
    private String employeeNumber;
    private Integer facilityId;
}
