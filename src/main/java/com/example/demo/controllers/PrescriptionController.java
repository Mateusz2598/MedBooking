package com.example.demo.controllers;

import com.example.demo.dtos.PrescriptionDto;
import com.example.demo.entities.Prescription;
import com.example.demo.requests.PrescriptionRequest;
import com.example.demo.response.PrescriptionResponse;
import com.example.demo.services.PrescriptionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("create")
    ResponseEntity<PrescriptionDto> createPrescription(@RequestBody @Valid PrescriptionDto prescriptionDto) {
        return ResponseEntity.ok(prescriptionService.createPrescription(prescriptionDto));
    }

    @GetMapping
    ResponseEntity<PrescriptionDto> getPrescriptionByID(@RequestParam Integer id) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }

    @GetMapping("getAll")
    ResponseEntity<List<PrescriptionDto>> getAllPrescription() {
        return ResponseEntity.ok(prescriptionService.getAllPrescription());
    }

    @GetMapping("forPharmacy")
    ResponseEntity<PrescriptionResponse> getPrescriptionPharmacy(@RequestBody @Valid PrescriptionRequest request) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionPharmacy(request));
    }

    @DeleteMapping
    ResponseEntity<Void> deletePrescription(@RequestParam Integer id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}
