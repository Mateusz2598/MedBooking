package com.example.demo.controllers;

import com.example.demo.dtos.MedicinDto;
import com.example.demo.entities.Medicin;
import com.example.demo.services.MedicinService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicin")
public class MedicinController {

    private final MedicinService medicinService;

    public MedicinController(MedicinService medicinService) {
        this.medicinService = medicinService;
    }

    @PostMapping("create")
    ResponseEntity<MedicinDto> createMedicin (@RequestBody @Valid MedicinDto medicinDto){
        return ResponseEntity.ok(medicinService.createMedicin(medicinDto));
    }

    @GetMapping
    ResponseEntity<MedicinDto> getMedicinById (@RequestParam Integer id){
        return ResponseEntity.ok(medicinService.getMedicinById(id));
    }

    @GetMapping("all")
    ResponseEntity<List<MedicinDto>> getAllMedicin (){

        return ResponseEntity.ok(medicinService.getAllMedicin());
    }

    @DeleteMapping
    ResponseEntity<Void> deleteMedicinById (@RequestParam Integer id){
        medicinService.deleteMedicinById(id);
        return ResponseEntity.noContent().build();
    }
}
