package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prescription {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String code;
    private LocalDate issureDate;
    private LocalDate expirationDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (name = "medicin_prescription", joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "medicin_id"))
    private List<Medicin> medicin;
    private Integer doctorId;
    private Integer patientId;
}
