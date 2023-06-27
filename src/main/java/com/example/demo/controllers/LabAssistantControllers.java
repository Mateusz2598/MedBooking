package com.example.demo.controllers;

import com.example.demo.dtos.LabAssistantDto;
import com.example.demo.response.LabAssistantResponse;
import com.example.demo.services.LabAssistantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("labAssistant")
public class LabAssistantControllers {
    private final LabAssistantService labAssistantService;

    public LabAssistantControllers(LabAssistantService labAssistantService) {
        this.labAssistantService = labAssistantService;
    }

    @PostMapping("create")
    ResponseEntity<LabAssistantResponse> createLabAssistant(@RequestBody @Valid LabAssistantDto labAssistantDto) {
        return ResponseEntity.ok(labAssistantService.createLabAssistant(labAssistantDto));
    }

    @GetMapping
    ResponseEntity<LabAssistantResponse>  getLabAssistantById(@RequestParam Integer id) {
        return ResponseEntity.ok(labAssistantService.getLabAssistantById(id));
    }

    @GetMapping("all")
    ResponseEntity<List<LabAssistantResponse>>  getAllLabAssistant() {
        return ResponseEntity.ok(labAssistantService.getAllLabAssistant());
    }

    @DeleteMapping
    ResponseEntity<Void> deleteLabAssistant(@RequestParam Integer id) {
        labAssistantService.deleteLabAssistant(id);
        return ResponseEntity.noContent().build();
    }
}
