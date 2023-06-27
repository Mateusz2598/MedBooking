package com.example.demo.entities;

import com.example.demo.enums.LaboratoryTestName;
import com.example.demo.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaboratoryTest {

    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "set_laboratory_test",joinColumns = @JoinColumn(name = "LaboratoryTest_id"))
    private Set<LaboratoryTestName> setLaboratoryTest;
    @Column(name = "recommendations_before_test")
    private String recommendations;
    private LocalDateTime dateTime;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;
    private Integer patientId;
    private Integer doctorId;
    private Integer labAssistantId;
}
