package com.example.demo.repositories;

import com.example.demo.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    @Query("SELECT p FROM Prescription p JOIN Patient pa ON p.patientId = pa.id WHERE p.code= :code and pa.pesel = :pesel")
    Optional<Prescription> findByCodeAndPesel(@Param("code") String code,@Param("pesel") String pesel);
}
