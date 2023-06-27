package com.example.demo.controllers;

import com.example.demo.dtos.PatientDto;
import com.example.demo.entities.Patient;
import com.example.demo.requests.CreatePatientRequests;
import com.example.demo.requests.PeselRequest;
import com.example.demo.requests.UpdatePatientRequest;
import com.example.demo.response.PatientResponse;
import com.example.demo.response.PatientUpdateResponse;
import com.example.demo.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patient")

public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PatchMapping
    ResponseEntity<PatientUpdateResponse> updateByPhoneMailAddress(@RequestBody @Valid UpdatePatientRequest updatePatientRequest, @RequestParam Integer patientId) {
        return ResponseEntity.ok(patientService.updateByPhoneMailAddress(updatePatientRequest, patientId));
    }

    @GetMapping("pesel")
    ResponseEntity<PatientDto> getPatientByPesel(@RequestBody PeselRequest peselRequest) {
        return ResponseEntity.ok(patientService.getPatientByPesel(peselRequest));
    }

    @PostMapping("create")
    ResponseEntity<PatientResponse> createPatient(@RequestBody @Valid CreatePatientRequests createPatientRequests) {
        return ResponseEntity.ok(patientService.createPatient(createPatientRequests));
    }

    @GetMapping
    ResponseEntity<PatientDto> getPatientById(@RequestParam Integer id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("getAll")
    ResponseEntity<List<PatientResponse>> getAllPatient() {
        return ResponseEntity.ok(patientService.getAllPatient());
    }

    @DeleteMapping
    ResponseEntity<Void> deletePatient(@RequestParam Integer id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
