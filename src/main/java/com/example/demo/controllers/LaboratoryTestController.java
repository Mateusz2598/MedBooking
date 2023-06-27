package com.example.demo.controllers;

import com.example.demo.dtos.LaboratoryTestDto;
import com.example.demo.response.LaboratoryTestResponse;
import com.example.demo.services.LaboratoryTestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("test")
public class LaboratoryTestController {

    private final LaboratoryTestService laboratoryTestService;

    public LaboratoryTestController(LaboratoryTestService laboratoryTestService) {
        this.laboratoryTestService = laboratoryTestService;
    }

    @PatchMapping
    ResponseEntity<LaboratoryTestResponse> addAssistentToTest(@RequestParam Integer idLabAssistant, @RequestParam Integer idLaboratoryTest){
        return ResponseEntity.ok(laboratoryTestService.addAssistentToTest(idLaboratoryTest,idLabAssistant));
    }

    @PostMapping("create")
    ResponseEntity<LaboratoryTestResponse> createTest(@RequestBody @Valid LaboratoryTestDto testDto) {
        return ResponseEntity.ok(laboratoryTestService.createLaboratoryTest(testDto));
    }

    @GetMapping
    ResponseEntity<LaboratoryTestResponse> getTestById(@RequestParam Integer id) {
        return ResponseEntity.ok(laboratoryTestService.getTestById(id));
    }

    @GetMapping("all")
    ResponseEntity <List<LaboratoryTestResponse>> getAllTest() {
        return ResponseEntity.ok(laboratoryTestService.getAllTest());
    }

    @DeleteMapping
    ResponseEntity<Void> deleteTest(@RequestParam Integer id) {
        laboratoryTestService.deleteLaboratoryTestById(id);
        return ResponseEntity.noContent().build();
    }
}
