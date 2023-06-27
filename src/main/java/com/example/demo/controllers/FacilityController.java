package com.example.demo.controllers;

import com.example.demo.dtos.FacilityDto;
import com.example.demo.entities.Facility;
import com.example.demo.enums.Specialization;
import com.example.demo.requests.CreateFacilityRequest;
import com.example.demo.response.FacilityResponse;
import com.example.demo.services.FacilityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("facility")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @PostMapping("create")
    ResponseEntity<FacilityResponse> createFacility (@RequestBody @Valid CreateFacilityRequest createFacilityRequest){
        return ResponseEntity.ok(facilityService.createFacility(createFacilityRequest));
    }

    @GetMapping
    ResponseEntity<FacilityResponse> getFacilityById (@RequestParam Integer id){
        return ResponseEntity.ok(facilityService.getFacilityById(id));
    }

    @GetMapping("all")
    ResponseEntity<List<FacilityResponse>> getAllFacility (){
        return ResponseEntity.ok(facilityService.getAllFacility());
    }

    @GetMapping("specjalization")
    ResponseEntity<Set<Specialization>> specjalizationFacility (Integer facilityId){
        return ResponseEntity.ok(facilityService.specjalizationFacility(facilityId));
    }

    @DeleteMapping
    ResponseEntity<Void> deleteFacilityById (@RequestParam Integer id){
        facilityService.deleteFacilityById(id);
        return ResponseEntity.noContent().build();
    }
}
