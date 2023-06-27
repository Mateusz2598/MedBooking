package com.example.demo.dtos;

import com.example.demo.enums.MedicalExaminationEnums;
import com.example.demo.enums.Specialization;
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
public class SkillsDto {

    private Integer id;
    @NotNull (message = "Add specializations.")
    private Specialization specialization;
    @NotNull (message = "Medical Examination cannot be null. Add value to it.")
    private Set<MedicalExaminationEnums> medicalExaminationEnums;
}
