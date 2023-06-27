package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String pesel;
    @Column(unique = true)
    private String numberPhone;
    @Column(name = "E_mail" ,unique = true)
    private String mail;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToMany(mappedBy = "patientId")
    private List<ConsultationsPhone> consultations;
    @OneToMany
    @JoinColumn(name = "patientId")
    private List<StationaryVisit> stationaryVisits;
    @OneToMany
    @JoinColumn(name = "patientId")
    private List<LaboratoryTest> ListLaboratoryTest;
    @OneToMany
    @JoinColumn(name = "patientId")
    private List<Prescription> prescriptions;
}
