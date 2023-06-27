package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabAssistant {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String employeeNumber;
    @OneToMany
    @JoinColumn(name = "labAssistantId")
    private Set<LaboratoryTest> laboratoryTest;
    @ManyToOne(fetch = FetchType.EAGER)
    private Facility facility;
}
