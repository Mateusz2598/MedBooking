package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medical_facility")
@Builder
public class Facility {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToMany(mappedBy = "facility")
    private List<StationaryVisit> stationaryVisit;
    @ManyToMany(mappedBy = "facilitySet")
    private List<Doctor> doctorSet;
    @OneToMany(mappedBy = "facility")
    private List<LabAssistant> labAssistants;

}
