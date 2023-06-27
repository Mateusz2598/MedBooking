package com.example.demo.controllers;

import com.example.demo.dtos.ConsultationsPhoneDto;
import com.example.demo.requests.CreateConsultationsRequest;
import com.example.demo.response.PatientVisitAndConsultationResponse;
import com.example.demo.services.ConsultationsPhoneService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("consultations")
public class ConsultationsPhoneController {

    private final ConsultationsPhoneService consultationsService;

    public ConsultationsPhoneController(ConsultationsPhoneService consultationsService) {
        this.consultationsService = consultationsService;
    }

    @PostMapping("create")
    ResponseEntity<ConsultationsPhoneDto> createConsultations (@RequestBody @Valid CreateConsultationsRequest createRequest){
        return ResponseEntity.ok(consultationsService.createConsultation(createRequest));
    }

    @GetMapping
    ResponseEntity<ConsultationsPhoneDto> getVisitById(@RequestParam Integer id) {
        return ResponseEntity.ok(consultationsService.getConsultationById(id));
    }

    @GetMapping("all")
    ResponseEntity<List<ConsultationsPhoneDto>> getAllConsultation(){
        return ResponseEntity.ok(consultationsService.getAllConsultation());
    }

    @GetMapping("all/appointments")
    ResponseEntity<List<PatientVisitAndConsultationResponse>> findAllAppointmentsPatients
            (@RequestParam Integer doctorId, @RequestParam LocalDate localDate){
        return ResponseEntity.ok(consultationsService.findAllAppointmentsPatients(doctorId,localDate));
    }

    @PatchMapping("booking")
    ResponseEntity<ConsultationsPhoneDto> consultationBooking(@RequestParam Integer patientId, @RequestParam Integer consultationId){
        return ResponseEntity.ok(consultationsService.consultationBooking(patientId, consultationId));
    }

    @PatchMapping("cancellation")
    ResponseEntity<ConsultationsPhoneDto> cancellationBooking (@RequestParam Integer consultationId){
        return ResponseEntity.ok(consultationsService.cancellationConsultations(consultationId));
    }

    @DeleteMapping
    ResponseEntity<PatientVisitAndConsultationResponse> deletedConsultationsById (@RequestParam Integer id){
        return ResponseEntity.ok(consultationsService.deleteConsultationsById(id));
    }

    @DeleteMapping("deleteAllOnDay")
    ResponseEntity<List<PatientVisitAndConsultationResponse>> deletedConsultationsByDoctorIdAndDay
            (@RequestParam Integer doctorId, @RequestParam LocalDate localDate){
        return ResponseEntity.ok(consultationsService.deleteConsultationsByDoctorIdAndDay(doctorId,localDate));
    }
}
