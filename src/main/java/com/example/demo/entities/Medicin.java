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
public class Medicin {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;
    private String dose;
    @Column(name = "package_quantity")
    private Integer quantity;
    @ManyToMany(mappedBy = "medicin", fetch = FetchType.LAZY)
    private List<Prescription> prescription;
}
