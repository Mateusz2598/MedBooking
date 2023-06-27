package com.example.demo.entities;

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
public class Doctor {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String employeeNumber;
    @OneToMany
    @JoinColumn(name = "doctorId")
    private List<StationaryVisit> stationaryVisit;
    @OneToMany(mappedBy = "doctorId")
    private List<ConsultationsPhone> eConsultations;
    @ManyToOne
    private Skills skills;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @ManyToMany
    @JoinTable(name = "facility_doctor", joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private List<Facility> facilitySet;
    @OneToMany
    @JoinColumn(name = "doctorId")
    private List<Prescription> prescription;
    @OneToMany
    @JoinColumn(name = "doctorId")
    private List<LaboratoryTest> laboratoryTest;


}
