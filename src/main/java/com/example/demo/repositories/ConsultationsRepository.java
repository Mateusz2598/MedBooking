package com.example.demo.repositories;

import com.example.demo.entities.ConsultationsPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationsRepository extends JpaRepository<ConsultationsPhone, Integer> {

    List<ConsultationsPhone> findByDateAndTimeAndDoctorId(LocalDate date, Time time, Integer doctorId);

    @Query("SELECT c FROM ConsultationsPhone c WHERE c.date = :date AND c.doctorId = :doctorId")
    List<ConsultationsPhone> findByDateAndDoctorId(@Param("date") LocalDate date, @Param("doctorId") Integer doctorId);

    @Query("SELECT c FROM ConsultationsPhone c WHERE c.date = :date AND c.doctorId = :doctorId and c.status = 'NOAVAILABLE'")
    List<ConsultationsPhone> findAllAppointments(@Param("date") LocalDate date, @Param("doctorId") Integer doctorId);

    @Query("SELECT SUM(c.price) FROM ConsultationsPhone c WHERE c.date >= :dateFrom AND c.date <= :dateTo AND" +
            " c.doctorId = :doctorId AND c.status = 'NOAVAILABLE'")
    BigDecimal sumSalaryDoctorConsultation(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo
            , @Param("doctorId") Integer doctorId);
}
