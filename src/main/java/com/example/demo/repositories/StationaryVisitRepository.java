package com.example.demo.repositories;

import com.example.demo.entities.StationaryVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface StationaryVisitRepository extends JpaRepository<StationaryVisit, Integer> {

    @Query("SELECT v FROM StationaryVisit v WHERE v.date = :date AND v.doctorId = :doctorId")
    List<StationaryVisit> findVisitByDateAndDoctorId(@Param("date") LocalDate date, @Param("doctorId") Integer doctorId);

    @Query("SELECT v FROM StationaryVisit v WHERE v.date = :date AND v.doctorId = :doctorId and v.status = 'NOAVAILABLE'")
    List<StationaryVisit> findAllVisitAppointments(@Param("date") LocalDate date, @Param("doctorId") Integer doctorId);

    @Query("SELECT SUM(v.price) FROM StationaryVisit v WHERE v.date > :dateFrom AND v.date < :dateTo AND" +
            " v.doctorId = :doctorId AND v.status = 'NOAVAILABLE'")
    BigDecimal sumSalaryDoctorVisit(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo
            , @Param("doctorId") Integer doctorId);
}
