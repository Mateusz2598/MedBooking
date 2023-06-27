package com.example.demo.entities;

import com.example.demo.enums.MedicalExaminationEnums;
import com.example.demo.enums.Specialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skills {

    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated (EnumType.STRING)
    private Specialization specialization;
    @Enumerated (EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "MedicalExamination", joinColumns = @JoinColumn(name = "skills_id"))
    private Set<MedicalExaminationEnums> medicalExaminationEnums;
}
