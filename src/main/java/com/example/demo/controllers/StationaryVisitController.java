package com.example.demo.controllers;

import com.example.demo.dtos.StationaryVisitDto;
import com.example.demo.requests.CreateVisitRequest;
import com.example.demo.response.PatientVisitAndConsultationResponse;
import com.example.demo.response.StationaryVisitResponse;
import com.example.demo.services.StationaryVisitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("stationaryVisit")
public class StationaryVisitController {

    private final StationaryVisitService stationaryVisitService;

    public StationaryVisitController(StationaryVisitService stationaryVisitService) {
        this.stationaryVisitService = stationaryVisitService;
    }

    @PostMapping("create")
    ResponseEntity<StationaryVisitResponse> createVisit (@RequestBody @Valid CreateVisitRequest createRequest){
        return ResponseEntity.ok(stationaryVisitService.createVisit(createRequest));
    }

    @GetMapping
    ResponseEntity<StationaryVisitResponse> getVisitById(@RequestParam Integer id) {
        return ResponseEntity.ok(stationaryVisitService.getVisitById(id));
    }


    @GetMapping("getAll")
    ResponseEntity<List<StationaryVisitResponse>> getAllVisit() {

        return ResponseEntity.ok(stationaryVisitService.getAllVisit());
    }

    @DeleteMapping
    ResponseEntity<PatientVisitAndConsultationResponse> deleteVisitById (@RequestParam Integer id){
        return ResponseEntity.ok(stationaryVisitService.deletedVisitById(id));

    }

    @GetMapping("allAppointments")
    ResponseEntity<List<PatientVisitAndConsultationResponse>> findAllAppointmentsPatientToVisit
            (@RequestParam Integer doctorId, @RequestParam LocalDate localDate){
        return ResponseEntity.ok(stationaryVisitService.findAllAppointmentsPatientToVisit(doctorId,localDate));
    }

    @PatchMapping("booking")
    ResponseEntity<StationaryVisitResponse> visitBooking(@RequestParam Integer patientId, @RequestParam Integer visitId){
        return ResponseEntity.ok(stationaryVisitService.visitBooking(patientId, visitId));
    }

    @PatchMapping("cancellation")
    ResponseEntity<StationaryVisitResponse> cancellationVisit (@RequestParam Integer visitId){
        return ResponseEntity.ok(stationaryVisitService.cancellationVisit(visitId));
    }

    @DeleteMapping("deleteAllOnDay")
    ResponseEntity<List<PatientVisitAndConsultationResponse>> deleteVisitByDoctorIdAndDay
    (@RequestParam Integer doctorId, @RequestParam LocalDate localDate){
        return ResponseEntity.ok(stationaryVisitService.deleteVisitByDoctorIdAndDay(doctorId,localDate));
    }
}
