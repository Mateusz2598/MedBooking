package com.example.demo.repositories;

import com.example.demo.entities.Medicin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinRepository extends JpaRepository<Medicin, Integer> {
}
