package com.example.demo.controllers;

import com.example.demo.dtos.DoctorDto;
import com.example.demo.requests.CreateDoctorRequest;
import com.example.demo.response.DoctorResponse;
import com.example.demo.services.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("create")
    ResponseEntity<DoctorResponse> createDoctor(@RequestBody @Valid CreateDoctorRequest doctorRequest) {
        return ResponseEntity.ok(doctorService.createDoctor(doctorRequest));
    }

    @GetMapping
    ResponseEntity<DoctorResponse> getDoctorById(@RequestParam Integer id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping("getAll")
    ResponseEntity<List<DoctorResponse>> getAllDoctor (){
        return ResponseEntity.ok(doctorService.getAllDoctor());
    }

    @DeleteMapping
    ResponseEntity<Void> deletedDoctorById (@RequestParam Integer id){
        doctorService.deletedDoctorById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("addFacility")
    ResponseEntity<DoctorResponse> addFacilityToDoctor(@RequestParam Integer doctorId, @RequestParam Integer facilityId){
        return ResponseEntity.ok(doctorService.addFacilityToDoctor(doctorId,facilityId));
    }

    @GetMapping("salaryAllVisits")
    ResponseEntity<Map> salaryAllDoctorsFromVisits(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo){
        return ResponseEntity.ok(doctorService.salaryAllDoctorsFromVisits(dateFrom,dateTo));
    }

    @GetMapping("salaryAllConsultation")
    ResponseEntity<Map> salaryAllDoctorsFromConsultation(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo){
        return ResponseEntity.ok(doctorService.salaryAllDoctorsFromConsultation(dateFrom,dateTo));
    }

    @PatchMapping("deleteFacility")
    ResponseEntity<DoctorResponse> deleteFacilityToDoctor(@RequestParam Integer doctorId, @RequestParam Integer facilityId){
        return ResponseEntity.ok(doctorService.deleteFacilityToDoctor(doctorId,facilityId));
    }

}
